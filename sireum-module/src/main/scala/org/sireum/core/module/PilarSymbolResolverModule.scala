// Do not edit this file. It is auto-generated from org.sireum.core.module.PilarSymbolResolver
// by org.sireum.pipeline.gen.ModuleGenerator

package org.sireum.core.module

import org.sireum.util._
import org.sireum.pipeline._
import org.sireum.pilar.ast.Model
import org.sireum.pilar.symbol.SymbolTable
import scala.Option
import scala.collection.immutable.Seq

object PilarSymbolResolverModule extends PipelineModule {
  def title = "Pilar Symbol Resolver"
  def origin = classOf[PilarSymbolResolver]

  val globalParallelKey = "Global.parallel"
  val globalHasExistingModelsKey = "Global.hasExistingModels"
  val globalModelsKey = "Global.models"
  val modelsKey = "PilarSymbolResolver.models"
  val globalHasExistingSymbolTableKey = "Global.hasExistingSymbolTable"
  val globalSymbolTableKey = "Global.symbolTable"

  def compute(job : PipelineJob, info : PipelineJobModuleInfo) : MBuffer[Tag] = {
    val tags = marrayEmpty[Tag]
    try {
      val module = Class.forName("org.sireum.core.module.PilarSymbolResolverModuleDef")
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
    val deps = ilist[PipelineModule](PilarParserModule)
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
    var _models : scala.Option[AnyRef] = None
    var _modelsKey : scala.Option[String] = None

    val keylistmodels = List(PilarSymbolResolverModule.globalModelsKey, PilarParserModule.modelsKey)
    keylistmodels.foreach(key =>
      if (job ? key) {
        if (_models.isEmpty) {
          _models = Some(job(key))
          _modelsKey = Some(key)
        }
        if (!(job(key).asInstanceOf[AnyRef] eq _models.get)) {
          tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
            "Input error for '" + this.title + "': 'models' keys '" + _modelsKey.get + " and '" + key + "' point to different objects.")
        }
      }
    )

    _models match {
      case Some(x) =>
        if (!x.isInstanceOf[scala.collection.immutable.Seq[org.sireum.pilar.ast.Model]]) {
          tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
            "Input error for '" + this.title + "': Wrong type found for 'models'.  Expecting 'scala.collection.immutable.Seq[org.sireum.pilar.ast.Model]' but found '" + x.getClass.toString + "'")
        }
      case None =>
        tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
          "Input error for '" + this.title + "': No value found for 'models'")
    }
    var _parallel : scala.Option[AnyRef] = None
    var _parallelKey : scala.Option[String] = None

    val keylistparallel = List(PilarSymbolResolverModule.globalParallelKey)
    keylistparallel.foreach(key =>
      if (job ? key) {
        if (_parallel.isEmpty) {
          _parallel = Some(job(key))
          _parallelKey = Some(key)
        }
        if (!(job(key).asInstanceOf[AnyRef] eq _parallel.get)) {
          tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
            "Input error for '" + this.title + "': 'parallel' keys '" + _parallelKey.get + " and '" + key + "' point to different objects.")
        }
      }
    )

    _parallel match {
      case Some(x) =>
        if (!x.isInstanceOf[scala.Boolean]) {
          tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
            "Input error for '" + this.title + "': Wrong type found for 'parallel'.  Expecting 'scala.Boolean' but found '" + x.getClass.toString + "'")
        }
      case None =>
        tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
          "Input error for '" + this.title + "': No value found for 'parallel'")
    }
    var _hasExistingSymbolTable : scala.Option[AnyRef] = None
    var _hasExistingSymbolTableKey : scala.Option[String] = None

    val keylisthasExistingSymbolTable = List(PilarSymbolResolverModule.globalHasExistingSymbolTableKey)
    keylisthasExistingSymbolTable.foreach(key =>
      if (job ? key) {
        if (_hasExistingSymbolTable.isEmpty) {
          _hasExistingSymbolTable = Some(job(key))
          _hasExistingSymbolTableKey = Some(key)
        }
        if (!(job(key).asInstanceOf[AnyRef] eq _hasExistingSymbolTable.get)) {
          tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
            "Input error for '" + this.title + "': 'hasExistingSymbolTable' keys '" + _hasExistingSymbolTableKey.get + " and '" + key + "' point to different objects.")
        }
      }
    )

    _hasExistingSymbolTable match {
      case Some(x) =>
        if (!x.isInstanceOf[scala.Option[org.sireum.pilar.symbol.SymbolTable]]) {
          tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
            "Input error for '" + this.title + "': Wrong type found for 'hasExistingSymbolTable'.  Expecting 'scala.Option[org.sireum.pilar.symbol.SymbolTable]' but found '" + x.getClass.toString + "'")
        }
      case None =>
        tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
          "Input error for '" + this.title + "': No value found for 'hasExistingSymbolTable'")
    }
    var _hasExistingModels : scala.Option[AnyRef] = None
    var _hasExistingModelsKey : scala.Option[String] = None

    val keylisthasExistingModels = List(PilarSymbolResolverModule.globalHasExistingModelsKey)
    keylisthasExistingModels.foreach(key =>
      if (job ? key) {
        if (_hasExistingModels.isEmpty) {
          _hasExistingModels = Some(job(key))
          _hasExistingModelsKey = Some(key)
        }
        if (!(job(key).asInstanceOf[AnyRef] eq _hasExistingModels.get)) {
          tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
            "Input error for '" + this.title + "': 'hasExistingModels' keys '" + _hasExistingModelsKey.get + " and '" + key + "' point to different objects.")
        }
      }
    )

    _hasExistingModels match {
      case Some(x) =>
        if (!x.isInstanceOf[scala.Option[scala.collection.immutable.Seq[org.sireum.pilar.ast.Model]]]) {
          tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
            "Input error for '" + this.title + "': Wrong type found for 'hasExistingModels'.  Expecting 'scala.Option[scala.collection.immutable.Seq[org.sireum.pilar.ast.Model]]' but found '" + x.getClass.toString + "'")
        }
      case None =>
        tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
          "Input error for '" + this.title + "': No value found for 'hasExistingModels'")
    }
    return tags
  }

  def outputDefined(job : PipelineJob) : MBuffer[Tag] = {
    val tags = marrayEmpty[Tag]
    if (!(job ? PilarSymbolResolverModule.modelsKey) && !(job ? PilarSymbolResolverModule.globalModelsKey)) {
      tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
        "Output error for '" + this.title + "': No entry found for 'models'. Expecting (PilarSymbolResolverModule.modelsKey or PilarSymbolResolverModule.globalModelsKey)")
    }

    if (job ? PilarSymbolResolverModule.modelsKey && !job(PilarSymbolResolverModule.modelsKey).isInstanceOf[scala.collection.immutable.Seq[org.sireum.pilar.ast.Model]]) {
      tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
        "Output error for '" + this.title + "': Wrong type found for PilarSymbolResolverModule.modelsKey.  Expecting 'scala.collection.immutable.Seq[org.sireum.pilar.ast.Model]' but found '" +
          job(PilarSymbolResolverModule.modelsKey).getClass.toString + "'")
    }

    if (job ? PilarSymbolResolverModule.globalModelsKey && !job(PilarSymbolResolverModule.globalModelsKey).isInstanceOf[scala.collection.immutable.Seq[org.sireum.pilar.ast.Model]]) {
      tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
        "Output error for '" + this.title + "': Wrong type found for PilarSymbolResolverModule.globalModelsKey.  Expecting 'scala.collection.immutable.Seq[org.sireum.pilar.ast.Model]' but found '" +
          job(PilarSymbolResolverModule.globalModelsKey).getClass.toString + "'")
    }

    if (!(job ? PilarSymbolResolverModule.globalSymbolTableKey)) {
      tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
        "Output error for '" + this.title + "': No entry found for 'symbolTable'. Expecting (PilarSymbolResolverModule.globalSymbolTableKey)")
    }

    if (job ? PilarSymbolResolverModule.globalSymbolTableKey && !job(PilarSymbolResolverModule.globalSymbolTableKey).isInstanceOf[org.sireum.pilar.symbol.SymbolTable]) {
      tags += PipelineUtil.genTag(PipelineUtil.ErrorMarker,
        "Output error for '" + this.title + "': Wrong type found for PilarSymbolResolverModule.globalSymbolTableKey.  Expecting 'org.sireum.pilar.symbol.SymbolTable' but found '" +
          job(PilarSymbolResolverModule.globalSymbolTableKey).getClass.toString + "'")
    }
    return tags
  }

  def getModels(options : scala.collection.Map[Property.Key, Any]) : scala.collection.immutable.Seq[org.sireum.pilar.ast.Model] = {
    if (options.contains(PilarSymbolResolverModule.globalModelsKey)) {
      return options(PilarSymbolResolverModule.globalModelsKey).asInstanceOf[scala.collection.immutable.Seq[org.sireum.pilar.ast.Model]]
    }
    if (options.contains(PilarSymbolResolverModule.modelsKey)) {
      return options(PilarSymbolResolverModule.modelsKey).asInstanceOf[scala.collection.immutable.Seq[org.sireum.pilar.ast.Model]]
    }
    if (options.contains(PilarParserModule.modelsKey)) {
      return options(PilarParserModule.modelsKey).asInstanceOf[scala.collection.immutable.Seq[org.sireum.pilar.ast.Model]]
    }

    throw new Exception("Pipeline checker should guarantee we never reach here")
  }

  def setModels(options : MMap[Property.Key, Any], models : scala.collection.immutable.Seq[org.sireum.pilar.ast.Model]) : MMap[Property.Key, Any] = {

    options(PilarSymbolResolverModule.globalModelsKey) = models
    options(modelsKey) = models

    return options
  }

  def getParallel(options : scala.collection.Map[Property.Key, Any]) : scala.Boolean = {
    if (options.contains(PilarSymbolResolverModule.globalParallelKey)) {
      return options(PilarSymbolResolverModule.globalParallelKey).asInstanceOf[scala.Boolean]
    }

    throw new Exception("Pipeline checker should guarantee we never reach here")
  }

  def setParallel(options : MMap[Property.Key, Any], parallel : scala.Boolean) : MMap[Property.Key, Any] = {

    options(PilarSymbolResolverModule.globalParallelKey) = parallel

    return options
  }

  def getHasExistingSymbolTable(options : scala.collection.Map[Property.Key, Any]) : scala.Option[org.sireum.pilar.symbol.SymbolTable] = {
    if (options.contains(PilarSymbolResolverModule.globalHasExistingSymbolTableKey)) {
      return options(PilarSymbolResolverModule.globalHasExistingSymbolTableKey).asInstanceOf[scala.Option[org.sireum.pilar.symbol.SymbolTable]]
    }

    throw new Exception("Pipeline checker should guarantee we never reach here")
  }

  def setHasExistingSymbolTable(options : MMap[Property.Key, Any], hasExistingSymbolTable : scala.Option[org.sireum.pilar.symbol.SymbolTable]) : MMap[Property.Key, Any] = {

    options(PilarSymbolResolverModule.globalHasExistingSymbolTableKey) = hasExistingSymbolTable

    return options
  }

  def getHasExistingModels(options : scala.collection.Map[Property.Key, Any]) : scala.Option[scala.collection.immutable.Seq[org.sireum.pilar.ast.Model]] = {
    if (options.contains(PilarSymbolResolverModule.globalHasExistingModelsKey)) {
      return options(PilarSymbolResolverModule.globalHasExistingModelsKey).asInstanceOf[scala.Option[scala.collection.immutable.Seq[org.sireum.pilar.ast.Model]]]
    }

    throw new Exception("Pipeline checker should guarantee we never reach here")
  }

  def setHasExistingModels(options : MMap[Property.Key, Any], hasExistingModels : scala.Option[scala.collection.immutable.Seq[org.sireum.pilar.ast.Model]]) : MMap[Property.Key, Any] = {

    options(PilarSymbolResolverModule.globalHasExistingModelsKey) = hasExistingModels

    return options
  }

  def getSymbolTable(options : scala.collection.Map[Property.Key, Any]) : org.sireum.pilar.symbol.SymbolTable = {
    if (options.contains(PilarSymbolResolverModule.globalSymbolTableKey)) {
      return options(PilarSymbolResolverModule.globalSymbolTableKey).asInstanceOf[org.sireum.pilar.symbol.SymbolTable]
    }

    throw new Exception("Pipeline checker should guarantee we never reach here")
  }

  def setSymbolTable(options : MMap[Property.Key, Any], symbolTable : org.sireum.pilar.symbol.SymbolTable) : MMap[Property.Key, Any] = {

    options(PilarSymbolResolverModule.globalSymbolTableKey) = symbolTable

    return options
  }

  object ConsumerView {
    implicit class PilarSymbolResolverModuleConsumerView(val job : PropertyProvider) extends AnyVal {
      def models : scala.collection.immutable.Seq[org.sireum.pilar.ast.Model] = PilarSymbolResolverModule.getModels(job.propertyMap)
      def parallel : scala.Boolean = PilarSymbolResolverModule.getParallel(job.propertyMap)
      def hasExistingSymbolTable : scala.Option[org.sireum.pilar.symbol.SymbolTable] = PilarSymbolResolverModule.getHasExistingSymbolTable(job.propertyMap)
      def hasExistingModels : scala.Option[scala.collection.immutable.Seq[org.sireum.pilar.ast.Model]] = PilarSymbolResolverModule.getHasExistingModels(job.propertyMap)
      def symbolTable : org.sireum.pilar.symbol.SymbolTable = PilarSymbolResolverModule.getSymbolTable(job.propertyMap)
    }
  }

  object ProducerView {
    implicit class PilarSymbolResolverModuleProducerView(val job : PropertyProvider) extends AnyVal {

      def models_=(models : scala.collection.immutable.Seq[org.sireum.pilar.ast.Model]) { PilarSymbolResolverModule.setModels(job.propertyMap, models) }
      def models : scala.collection.immutable.Seq[org.sireum.pilar.ast.Model] = PilarSymbolResolverModule.getModels(job.propertyMap)

      def parallel_=(parallel : scala.Boolean) { PilarSymbolResolverModule.setParallel(job.propertyMap, parallel) }
      def parallel : scala.Boolean = PilarSymbolResolverModule.getParallel(job.propertyMap)

      def hasExistingSymbolTable_=(hasExistingSymbolTable : scala.Option[org.sireum.pilar.symbol.SymbolTable]) { PilarSymbolResolverModule.setHasExistingSymbolTable(job.propertyMap, hasExistingSymbolTable) }
      def hasExistingSymbolTable : scala.Option[org.sireum.pilar.symbol.SymbolTable] = PilarSymbolResolverModule.getHasExistingSymbolTable(job.propertyMap)

      def hasExistingModels_=(hasExistingModels : scala.Option[scala.collection.immutable.Seq[org.sireum.pilar.ast.Model]]) { PilarSymbolResolverModule.setHasExistingModels(job.propertyMap, hasExistingModels) }
      def hasExistingModels : scala.Option[scala.collection.immutable.Seq[org.sireum.pilar.ast.Model]] = PilarSymbolResolverModule.getHasExistingModels(job.propertyMap)

      def symbolTable_=(symbolTable : org.sireum.pilar.symbol.SymbolTable) { PilarSymbolResolverModule.setSymbolTable(job.propertyMap, symbolTable) }
      def symbolTable : org.sireum.pilar.symbol.SymbolTable = PilarSymbolResolverModule.getSymbolTable(job.propertyMap)
    }
  }
}

trait PilarSymbolResolverModule {
  def job : PipelineJob

  def models_=(models : scala.collection.immutable.Seq[org.sireum.pilar.ast.Model]) { PilarSymbolResolverModule.setModels(job.propertyMap, models) }
  def models : scala.collection.immutable.Seq[org.sireum.pilar.ast.Model] = PilarSymbolResolverModule.getModels(job.propertyMap)

  def parallel : scala.Boolean = PilarSymbolResolverModule.getParallel(job.propertyMap)

  def hasExistingSymbolTable : scala.Option[org.sireum.pilar.symbol.SymbolTable] = PilarSymbolResolverModule.getHasExistingSymbolTable(job.propertyMap)

  def hasExistingModels : scala.Option[scala.collection.immutable.Seq[org.sireum.pilar.ast.Model]] = PilarSymbolResolverModule.getHasExistingModels(job.propertyMap)

  def symbolTable_=(symbolTable : org.sireum.pilar.symbol.SymbolTable) { PilarSymbolResolverModule.setSymbolTable(job.propertyMap, symbolTable) }
  def symbolTable : org.sireum.pilar.symbol.SymbolTable = PilarSymbolResolverModule.getSymbolTable(job.propertyMap)
}