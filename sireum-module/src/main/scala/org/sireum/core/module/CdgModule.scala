// Do not edit this file. It is auto-generated from org.sireum.core.module.Cdg
// by org.sireum.pipeline.gen.ModuleGenerator

package org.sireum.core.module

import org.sireum.util._
import org.sireum.pipeline._
import java.lang.String
import org.sireum.alir.AlirIntraProceduralNode
import org.sireum.alir.ControlDependenceGraph
import org.sireum.alir.ControlFlowGraph
import org.sireum.alir.DataDependenceGraph
import org.sireum.alir.DefRef
import org.sireum.alir.DominanceFrontierGraph
import org.sireum.alir.ImmediateDominatorGraph
import org.sireum.alir.MonotoneDataFlowAnalysisResult
import org.sireum.core.module.AlirIntraProcedural.AlirIntraproceduralAnalysisResult
import org.sireum.pilar.ast.LocationDecl
import org.sireum.pilar.symbol.ProcedureSymbolTable
import org.sireum.pilar.symbol.SymbolTable
import scala.Function1
import scala.Function2
import scala.Option
import scala.collection.mutable.Map

object CdgModule extends PipelineModule {
  def title = "Control Dependence Graph Builder"
  def origin = classOf[Cdg]

  val globalCdgKey = "Global.cdg"
  val cdgKey = "Cdg.cdg"
  val globalPoolKey = "Global.pool"
  val cfgKey = "Cdg.cfg"
  val poolKey = "Cdg.pool"
  val globalCfgKey = "Global.cfg"

  def compute(job : PipelineJob, info : PipelineJobModuleInfo) : MBuffer[Tag] = {
    val tags = marrayEmpty[Tag]
    try {
      val module = Class.forName("org.sireum.core.module.CdgModuleDef")
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

    val keylistpool = List(CdgModule.globalPoolKey, CfgModule.poolKey)
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

    val keylistcfg = List(CdgModule.globalCfgKey, CfgModule.cfgKey)
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
    if (!(job ? CdgModule.cdgKey) && !(job ? CdgModule.globalCdgKey)) {
      tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
        "Output error for '" + this.title + "': No entry found for 'cdg'. Expecting (CdgModule.cdgKey or CdgModule.globalCdgKey)")
    }

    if (job ? CdgModule.cdgKey && !job(CdgModule.cdgKey).isInstanceOf[org.sireum.alir.ControlDependenceGraph[java.lang.String]]) {
      tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
        "Output error for '" + this.title + "': Wrong type found for CdgModule.cdgKey.  Expecting 'org.sireum.alir.ControlDependenceGraph[java.lang.String]' but found '" +
          job(CdgModule.cdgKey).getClass.toString + "'")
    }

    if (job ? CdgModule.globalCdgKey && !job(CdgModule.globalCdgKey).isInstanceOf[org.sireum.alir.ControlDependenceGraph[java.lang.String]]) {
      tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
        "Output error for '" + this.title + "': Wrong type found for CdgModule.globalCdgKey.  Expecting 'org.sireum.alir.ControlDependenceGraph[java.lang.String]' but found '" +
          job(CdgModule.globalCdgKey).getClass.toString + "'")
    }
    return tags
  }

  def getPool(options : scala.collection.Map[Property.Key, Any]) : scala.collection.mutable.Map[org.sireum.alir.AlirIntraProceduralNode, org.sireum.alir.AlirIntraProceduralNode] = {
    if (options.contains(CdgModule.globalPoolKey)) {
      return options(CdgModule.globalPoolKey).asInstanceOf[scala.collection.mutable.Map[org.sireum.alir.AlirIntraProceduralNode, org.sireum.alir.AlirIntraProceduralNode]]
    }
    if (options.contains(CdgModule.poolKey)) {
      return options(CdgModule.poolKey).asInstanceOf[scala.collection.mutable.Map[org.sireum.alir.AlirIntraProceduralNode, org.sireum.alir.AlirIntraProceduralNode]]
    }
    if (options.contains(CfgModule.poolKey)) {
      return options(CfgModule.poolKey).asInstanceOf[scala.collection.mutable.Map[org.sireum.alir.AlirIntraProceduralNode, org.sireum.alir.AlirIntraProceduralNode]]
    }

    throw new Exception("Pipeline checker should guarantee we never reach here")
  }

  def setPool(options : MMap[Property.Key, Any], pool : scala.collection.mutable.Map[org.sireum.alir.AlirIntraProceduralNode, org.sireum.alir.AlirIntraProceduralNode]) : MMap[Property.Key, Any] = {

    options(CdgModule.globalPoolKey) = pool
    options(poolKey) = pool

    return options
  }

  def getCfg(options : scala.collection.Map[Property.Key, Any]) : org.sireum.alir.ControlFlowGraph[java.lang.String] = {
    if (options.contains(CdgModule.globalCfgKey)) {
      return options(CdgModule.globalCfgKey).asInstanceOf[org.sireum.alir.ControlFlowGraph[java.lang.String]]
    }
    if (options.contains(CdgModule.cfgKey)) {
      return options(CdgModule.cfgKey).asInstanceOf[org.sireum.alir.ControlFlowGraph[java.lang.String]]
    }
    if (options.contains(CfgModule.cfgKey)) {
      return options(CfgModule.cfgKey).asInstanceOf[org.sireum.alir.ControlFlowGraph[java.lang.String]]
    }

    throw new Exception("Pipeline checker should guarantee we never reach here")
  }

  def setCfg(options : MMap[Property.Key, Any], cfg : org.sireum.alir.ControlFlowGraph[java.lang.String]) : MMap[Property.Key, Any] = {

    options(CdgModule.globalCfgKey) = cfg
    options(cfgKey) = cfg

    return options
  }

  def getCdg(options : scala.collection.Map[Property.Key, Any]) : org.sireum.alir.ControlDependenceGraph[java.lang.String] = {
    if (options.contains(CdgModule.globalCdgKey)) {
      return options(CdgModule.globalCdgKey).asInstanceOf[org.sireum.alir.ControlDependenceGraph[java.lang.String]]
    }
    if (options.contains(CdgModule.cdgKey)) {
      return options(CdgModule.cdgKey).asInstanceOf[org.sireum.alir.ControlDependenceGraph[java.lang.String]]
    }

    throw new Exception("Pipeline checker should guarantee we never reach here")
  }

  def setCdg(options : MMap[Property.Key, Any], cdg : org.sireum.alir.ControlDependenceGraph[java.lang.String]) : MMap[Property.Key, Any] = {

    options(CdgModule.globalCdgKey) = cdg
    options(cdgKey) = cdg

    return options
  }

  object ConsumerView {
    implicit class CdgModuleConsumerView(val job : PropertyProvider) extends AnyVal {
      def pool : scala.collection.mutable.Map[org.sireum.alir.AlirIntraProceduralNode, org.sireum.alir.AlirIntraProceduralNode] = CdgModule.getPool(job.propertyMap)
      def cfg : org.sireum.alir.ControlFlowGraph[java.lang.String] = CdgModule.getCfg(job.propertyMap)
      def cdg : org.sireum.alir.ControlDependenceGraph[java.lang.String] = CdgModule.getCdg(job.propertyMap)
    }
  }

  object ProducerView {
    implicit class CdgModuleProducerView(val job : PropertyProvider) extends AnyVal {

      def pool_=(pool : scala.collection.mutable.Map[org.sireum.alir.AlirIntraProceduralNode, org.sireum.alir.AlirIntraProceduralNode]) { CdgModule.setPool(job.propertyMap, pool) }
      def pool : scala.collection.mutable.Map[org.sireum.alir.AlirIntraProceduralNode, org.sireum.alir.AlirIntraProceduralNode] = CdgModule.getPool(job.propertyMap)

      def cfg_=(cfg : org.sireum.alir.ControlFlowGraph[java.lang.String]) { CdgModule.setCfg(job.propertyMap, cfg) }
      def cfg : org.sireum.alir.ControlFlowGraph[java.lang.String] = CdgModule.getCfg(job.propertyMap)

      def cdg_=(cdg : org.sireum.alir.ControlDependenceGraph[java.lang.String]) { CdgModule.setCdg(job.propertyMap, cdg) }
      def cdg : org.sireum.alir.ControlDependenceGraph[java.lang.String] = CdgModule.getCdg(job.propertyMap)
    }
  }
}

trait CdgModule {
  def job : PipelineJob

  def pool : scala.collection.mutable.Map[org.sireum.alir.AlirIntraProceduralNode, org.sireum.alir.AlirIntraProceduralNode] = CdgModule.getPool(job.propertyMap)

  def cfg : org.sireum.alir.ControlFlowGraph[java.lang.String] = CdgModule.getCfg(job.propertyMap)

  def cdg_=(cdg : org.sireum.alir.ControlDependenceGraph[java.lang.String]) { CdgModule.setCdg(job.propertyMap, cdg) }
  def cdg : org.sireum.alir.ControlDependenceGraph[java.lang.String] = CdgModule.getCdg(job.propertyMap)
}