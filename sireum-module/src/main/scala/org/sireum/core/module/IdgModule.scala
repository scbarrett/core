// Do not edit this file. It is auto-generated from org.sireum.core.module.Idg
// by org.sireum.pipeline.gen.ModuleGenerator

package org.sireum.core.module

import org.sireum.util._
import org.sireum.pipeline._
import java.lang.String
import org.sireum.alir.AlirIntraProceduralNode
import org.sireum.alir.ControlFlowGraph
import org.sireum.alir.DefRef
import org.sireum.alir.ImmediateDominatorGraph
import org.sireum.core.module.AlirIntraProcedural.AlirIntraproceduralAnalysisResult
import org.sireum.pilar.ast.LocationDecl
import org.sireum.pilar.symbol.ProcedureSymbolTable
import org.sireum.pilar.symbol.SymbolTable
import scala.Function1
import scala.Function2
import scala.Option
import scala.collection.mutable.Map

object IdgModule extends PipelineModule {
  def title = "Immediate Dominator Graph Builder"
  def origin = classOf[Idg]

  val cfgKey = "Idg.cfg"
  val globalPoolKey = "Global.pool"
  val poolKey = "Idg.pool"
  val globalIdgKey = "Global.idg"
  val idgKey = "Idg.idg"
  val globalCfgKey = "Global.cfg"

  def compute(job : PipelineJob, info : PipelineJobModuleInfo) : MBuffer[Tag] = {
    val tags = marrayEmpty[Tag]
    try {
      val module = Class.forName("org.sireum.core.module.IdgModuleDef")
      val cons = module.getConstructors()(0)
      val params = Array[AnyRef](job, info)
      val inst = cons.newInstance(params : _*)
    } catch {
      case e : Throwable =>
        e.printStackTrace
        tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker, e.getMessage);
    }
    return tags
  }

  override def initialize(job : PipelineJob) {
  }

  override def validPipeline(stage : PipelineStage, job : PipelineJob) : MBuffer[Tag] = {
    val tags = marrayEmpty[Tag]
    val deps = ilist[PipelineModule](CfgModule, CfgModule)
    deps.foreach(d =>
      if (stage.modules.contains(d)) {
        tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
          "'" + this.title + "' depends on '" + d.title + "' yet both were found in stage '" + stage.title + "'"
        )
      }
    )
    return tags
  }

  def inputDefined(job : PipelineJob) : MBuffer[Tag] = {
    val tags = marrayEmpty[Tag]
    var _pool : scala.Option[AnyRef] = None
    var _poolKey : scala.Option[String] = None

    val keylistpool = List(IdgModule.globalPoolKey, CfgModule.poolKey)
    keylistpool.foreach(key =>
      if (job ? key) {
        if (_pool.isEmpty) {
          _pool = Some(job(key))
          _poolKey = Some(key)
        }
        if (!(job(key).asInstanceOf[AnyRef] eq _pool.get)) {
          tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
            "Input error for '" + this.title + "': 'pool' keys '" + _poolKey.get + " and '" + key + "' point to different objects.")
        }
      }
    )

    _pool match {
      case Some(x) =>
        if (!x.isInstanceOf[scala.collection.mutable.Map[org.sireum.alir.AlirIntraProceduralNode, org.sireum.alir.AlirIntraProceduralNode]]) {
          tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
            "Input error for '" + this.title + "': Wrong type found for 'pool'.  Expecting 'scala.collection.mutable.Map[org.sireum.alir.AlirIntraProceduralNode, org.sireum.alir.AlirIntraProceduralNode]' but found '" + x.getClass.toString + "'")
        }
      case None =>
        tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
          "Input error for '" + this.title + "': No value found for 'pool'")
    }
    var _cfg : scala.Option[AnyRef] = None
    var _cfgKey : scala.Option[String] = None

    val keylistcfg = List(IdgModule.globalCfgKey, CfgModule.cfgKey)
    keylistcfg.foreach(key =>
      if (job ? key) {
        if (_cfg.isEmpty) {
          _cfg = Some(job(key))
          _cfgKey = Some(key)
        }
        if (!(job(key).asInstanceOf[AnyRef] eq _cfg.get)) {
          tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
            "Input error for '" + this.title + "': 'cfg' keys '" + _cfgKey.get + " and '" + key + "' point to different objects.")
        }
      }
    )

    _cfg match {
      case Some(x) =>
        if (!x.isInstanceOf[org.sireum.alir.ControlFlowGraph[java.lang.String]]) {
          tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
            "Input error for '" + this.title + "': Wrong type found for 'cfg'.  Expecting 'org.sireum.alir.ControlFlowGraph[java.lang.String]' but found '" + x.getClass.toString + "'")
        }
      case None =>
        tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
          "Input error for '" + this.title + "': No value found for 'cfg'")
    }
    return tags
  }

  def outputDefined(job : PipelineJob) : MBuffer[Tag] = {
    val tags = marrayEmpty[Tag]
    if (!(job ? IdgModule.idgKey) && !(job ? IdgModule.globalIdgKey)) {
      tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
        "Output error for '" + this.title + "': No entry found for 'idg'. Expecting (IdgModule.idgKey or IdgModule.globalIdgKey)")
    }

    if (job ? IdgModule.idgKey && !job(IdgModule.idgKey).isInstanceOf[org.sireum.alir.ImmediateDominatorGraph[java.lang.String]]) {
      tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
        "Output error for '" + this.title + "': Wrong type found for IdgModule.idgKey.  Expecting 'org.sireum.alir.ImmediateDominatorGraph[java.lang.String]' but found '" +
          job(IdgModule.idgKey).getClass.toString + "'")
    }

    if (job ? IdgModule.globalIdgKey && !job(IdgModule.globalIdgKey).isInstanceOf[org.sireum.alir.ImmediateDominatorGraph[java.lang.String]]) {
      tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
        "Output error for '" + this.title + "': Wrong type found for IdgModule.globalIdgKey.  Expecting 'org.sireum.alir.ImmediateDominatorGraph[java.lang.String]' but found '" +
          job(IdgModule.globalIdgKey).getClass.toString + "'")
    }
    return tags
  }

  def getPool(options : scala.collection.Map[Property.Key, Any]) : scala.collection.mutable.Map[org.sireum.alir.AlirIntraProceduralNode, org.sireum.alir.AlirIntraProceduralNode] = {
    if (options.contains(IdgModule.globalPoolKey)) {
      return options(IdgModule.globalPoolKey).asInstanceOf[scala.collection.mutable.Map[org.sireum.alir.AlirIntraProceduralNode, org.sireum.alir.AlirIntraProceduralNode]]
    }
    if (options.contains(IdgModule.poolKey)) {
      return options(IdgModule.poolKey).asInstanceOf[scala.collection.mutable.Map[org.sireum.alir.AlirIntraProceduralNode, org.sireum.alir.AlirIntraProceduralNode]]
    }
    if (options.contains(CfgModule.poolKey)) {
      return options(CfgModule.poolKey).asInstanceOf[scala.collection.mutable.Map[org.sireum.alir.AlirIntraProceduralNode, org.sireum.alir.AlirIntraProceduralNode]]
    }

    throw new Exception("Pipeline checker should guarantee we never reach here")
  }

  def setPool(options : MMap[Property.Key, Any], pool : scala.collection.mutable.Map[org.sireum.alir.AlirIntraProceduralNode, org.sireum.alir.AlirIntraProceduralNode]) : MMap[Property.Key, Any] = {

    options(IdgModule.globalPoolKey) = pool
    options(poolKey) = pool

    return options
  }

  def getCfg(options : scala.collection.Map[Property.Key, Any]) : org.sireum.alir.ControlFlowGraph[java.lang.String] = {
    if (options.contains(IdgModule.globalCfgKey)) {
      return options(IdgModule.globalCfgKey).asInstanceOf[org.sireum.alir.ControlFlowGraph[java.lang.String]]
    }
    if (options.contains(IdgModule.cfgKey)) {
      return options(IdgModule.cfgKey).asInstanceOf[org.sireum.alir.ControlFlowGraph[java.lang.String]]
    }
    if (options.contains(CfgModule.cfgKey)) {
      return options(CfgModule.cfgKey).asInstanceOf[org.sireum.alir.ControlFlowGraph[java.lang.String]]
    }

    throw new Exception("Pipeline checker should guarantee we never reach here")
  }

  def setCfg(options : MMap[Property.Key, Any], cfg : org.sireum.alir.ControlFlowGraph[java.lang.String]) : MMap[Property.Key, Any] = {

    options(IdgModule.globalCfgKey) = cfg
    options(cfgKey) = cfg

    return options
  }

  def getIdg(options : scala.collection.Map[Property.Key, Any]) : org.sireum.alir.ImmediateDominatorGraph[java.lang.String] = {
    if (options.contains(IdgModule.globalIdgKey)) {
      return options(IdgModule.globalIdgKey).asInstanceOf[org.sireum.alir.ImmediateDominatorGraph[java.lang.String]]
    }
    if (options.contains(IdgModule.idgKey)) {
      return options(IdgModule.idgKey).asInstanceOf[org.sireum.alir.ImmediateDominatorGraph[java.lang.String]]
    }

    throw new Exception("Pipeline checker should guarantee we never reach here")
  }

  def setIdg(options : MMap[Property.Key, Any], idg : org.sireum.alir.ImmediateDominatorGraph[java.lang.String]) : MMap[Property.Key, Any] = {

    options(IdgModule.globalIdgKey) = idg
    options(idgKey) = idg

    return options
  }

  object ConsumerView {
    implicit class IdgModuleConsumerView(val job : PropertyProvider) extends AnyVal {
      def pool : scala.collection.mutable.Map[org.sireum.alir.AlirIntraProceduralNode, org.sireum.alir.AlirIntraProceduralNode] = IdgModule.getPool(job.propertyMap)
      def cfg : org.sireum.alir.ControlFlowGraph[java.lang.String] = IdgModule.getCfg(job.propertyMap)
      def idg : org.sireum.alir.ImmediateDominatorGraph[java.lang.String] = IdgModule.getIdg(job.propertyMap)
    }
  }

  object ProducerView {
    implicit class IdgModuleProducerView(val job : PropertyProvider) extends AnyVal {

      def pool_=(pool : scala.collection.mutable.Map[org.sireum.alir.AlirIntraProceduralNode, org.sireum.alir.AlirIntraProceduralNode]) { IdgModule.setPool(job.propertyMap, pool) }
      def pool : scala.collection.mutable.Map[org.sireum.alir.AlirIntraProceduralNode, org.sireum.alir.AlirIntraProceduralNode] = IdgModule.getPool(job.propertyMap)

      def cfg_=(cfg : org.sireum.alir.ControlFlowGraph[java.lang.String]) { IdgModule.setCfg(job.propertyMap, cfg) }
      def cfg : org.sireum.alir.ControlFlowGraph[java.lang.String] = IdgModule.getCfg(job.propertyMap)

      def idg_=(idg : org.sireum.alir.ImmediateDominatorGraph[java.lang.String]) { IdgModule.setIdg(job.propertyMap, idg) }
      def idg : org.sireum.alir.ImmediateDominatorGraph[java.lang.String] = IdgModule.getIdg(job.propertyMap)
    }
  }
}

trait IdgModule {
  def job : PipelineJob

  def pool : scala.collection.mutable.Map[org.sireum.alir.AlirIntraProceduralNode, org.sireum.alir.AlirIntraProceduralNode] = IdgModule.getPool(job.propertyMap)

  def cfg : org.sireum.alir.ControlFlowGraph[java.lang.String] = IdgModule.getCfg(job.propertyMap)

  def idg_=(idg : org.sireum.alir.ImmediateDominatorGraph[java.lang.String]) { IdgModule.setIdg(job.propertyMap, idg) }
  def idg : org.sireum.alir.ImmediateDominatorGraph[java.lang.String] = IdgModule.getIdg(job.propertyMap)
}