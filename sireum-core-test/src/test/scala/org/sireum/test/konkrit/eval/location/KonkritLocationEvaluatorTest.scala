/*
Copyright (c) 2011-2013 Robby, Kansas State University.        
All rights reserved. This program and the accompanying materials      
are made available under the terms of the Eclipse Public License v1.0 
which accompanies this distribution, and is available at              
http://www.eclipse.org/legal/epl-v10.html                             
*/

package org.sireum.test.konkrit.eval.location

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.junit.ShouldMatchersForJUnit
import org.sireum.extension._
import org.sireum.konkrit.extension._
import org.sireum.kiasan.state._
import org.sireum.pilar.ast._
import org.sireum.pilar.eval._
import org.sireum.pilar.state._
import org.sireum.util._
import org.sireum.util.math._
import org.sireum.test._
import org.sireum.test.framework.pilar.eval._
import org.sireum.test.konkrit.eval.exp._
import org.sireum.test.kiasan.eval._
import org.sireum.test.konkrit.eval.action.KonkritActionEvaluatorTestCases
import org.sireum.test.konkrit.eval.jump.KonkritJumpEvaluatorTestCases
import org.sireum.test.konkrit.eval.transformation.KonkritTransformationEvaluatorTestCases
import org.sireum.test.konkrit.eval.KonkritEvaluatorTestUtil

/**
 * @author <a href="mailto:robby@k-state.edu">Robby</a>
 */
@RunWith(classOf[JUnitRunner])
class KonkritLocationEvaluatorTest
    extends LocationEvaluatorTestFramework[BasicState, ISeq[(BasicState, Boolean)]]
    with KonkritLocationEvaluatorTestCases[BasicState] {

  type S = BasicState
  type V = Value
  type Re = (BasicState, Value)
  type R = ISeq[Re]
  type C = ISeq[(S, Boolean)]
  type Se = S
  type SR = ISeq[S]
  
  import org.sireum.test.konkrit.eval.KonkritEvaluatorTestUtil._

  def state = emptyState.enterCallFrame("proc", Some("init"), 0,
    imapEmpty, None, -1, ivectorEmpty)

  def emptyState = BasicState()

  def newLocationEvaluator(s : S) = 
    KonkritEvaluatorTestUtil.newEvaluator(None,
      KonkritVariableAccessExtension,
      KonkritBooleanExtension,
      KonkritIntegerExtension)
}
