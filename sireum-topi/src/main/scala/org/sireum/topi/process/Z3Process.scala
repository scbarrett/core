/*
Copyright (c) 2011-2012 Robby, Kansas State University.        
All rights reserved. This program and the accompanying materials      
are made available under the terms of the Eclipse Public License v1.0 
which accompanies this distribution, and is available at              
http://www.eclipse.org/legal/epl-v10.html                             
*/

package org.sireum.topi.process

import org.sireum.pilar.ast._
import org.sireum.topi._
import java.io._
import org.sireum.util._
import org.sireum.pilar.state._
import org.sireum.util.sexp.ast._

/**
 * @author <a href="mailto:robby@k-state.edu">Robby</a>
 */
class Z3Process(z3 : File, waitTime : Long, trans : TopiProcess.BackEndPart*) extends Topi {
  def stateRewriter(m : IMap[String, Value]) =
    PartialFunctionUtil.orElses[Any, Any](trans.map { _.stateRewriter(m) })

  def translate(conjuncts : Iterable[Exp]) : String = {

    val sw = new StringWriter
    val pw = new PrintWriter(sw)

    val t = PartialFunctionUtil.orElses[Exp, Unit](trans.map { _.expTranslator(pw) })

    for (c <- conjuncts) {
      assert(t isDefinedAt c, c.toString)
      t(c)
    }

    sw.toString
  }

  def exec(script : String) = {
    val z3Path = z3.getAbsolutePath
    val args =
      (OsArchUtil.detect : @unchecked) match {
        case OsArch.Win64 | OsArch.Win32 =>
          ilist(z3Path, "/smt2", "/in")
        case OsArch.Mac64 | OsArch.Mac32 | OsArch.Linux64 | OsArch.Linux32 =>
          ilist(z3Path, "-smt2", "-in")
      }

    val e = new Exec
    e.run(waitTime, args, Some(script))
  }

  def check(conjuncts : Iterable[Exp]) : TopiResult.Type = {
    import TopiResult._

    val script = translate(conjuncts)
    exec(script + "(check-sat)\n(exit)\n") match {
      case Exec.Timeout =>
        TIMEOUT
      case Exec.StringResult(s) =>
        s.trim match {
          case "sat"     => SAT
          case "unsat"   => UNSAT
          case "unknown" => UNKNOWN
          case s =>
            scala.Console.err.println(script)
            scala.Console.err.println(s)
            scala.Console.err.flush
            assert(false)
            sys.error("Unexpected result")
        }
      case Exec.ExceptionRaised(ex) =>
        throw ex
    }
  }

  def getModel(conjuncts : Iterable[Exp]) : Option[IMap[String, Value]] = {
    val script = translate(conjuncts)
    exec(script + "(check-sat)\n(get-model)\n(exit)\n") match {
      case Exec.Timeout             => None
      case Exec.StringResult(s)     => Some(Z3Process.parseModel(s))
      case Exec.ExceptionRaised(ex) => throw ex
    }
  }
}

/**
 * @author <a href="mailto:robby@k-state.edu">Robby</a>
 */
object Z3Process {
  def parseModel(model : String) : IMap[String, Value] = {
    val m = mmapEmpty[ResourceUri, Value]
    val e = SExprAst.build(model)
    Visitor.build({
      // (define-fun varName () varBasicType varValue)
      case ListSExpr(
        Seq(AtomSExpr("define-fun"), AtomSExpr(varName), ListSExpr(Seq()),
          AtomSExpr(varBasicType), varValue)) =>
        parseValue(varBasicType, varValue) match {
          case Some(v) => m(varName) = v
          case _       =>
        }
        false
    })(e)
    m.toMap
  }

  def parseValue(varBasicType : String, varValue : SExprAstNode) : Option[Value] = {
    import org.sireum.util.math._

    (varBasicType, varValue) match {
      case ("Bool", AtomSExpr(b)) =>
        Some(if (b == "false") Topi.Boolean(false) else Topi.Boolean(true))
      case ("Int", AtomSExpr(i)) =>
        Some(Topi.Integer(SireumNumber(BigInt(i)).pack))
      case ("Int", ListSExpr(Seq(AtomSExpr("-"), AtomSExpr(i)))) =>
        Some(Topi.Integer(SireumNumber(BigInt('-' + i)).pack))
      case ("Real", node : SExprAstNode) =>
        val (isPos, s) =
          node match {
            case AtomSExpr(s)                                 => (true, s)
            case ListSExpr(Seq(AtomSExpr("-"), AtomSExpr(s))) => (false, s)
          }
        val a = s.split('.')
        val c = a(0)
        val n = a(1)
        val d = new StringBuilder("1")
        for (i <- 0 until a(1).length)
          d.append('0')
        Some(Topi.Rational(SireumNumber(isPos, SireumNumber(BigInt(c)).pack,
          SireumNumber(BigInt(n)).pack, SireumNumber(BigInt(d.toString)).pack)))
      case _ =>
        None
    }
  }
}