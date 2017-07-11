package com.jetprobe.fastgen.generators

import com.jetprobe.fastgen.common.FileReader
import com.typesafe.config.Config

import scala.collection.mutable
import scala.util.Random
import scala.util.matching.Regex

/**
  * @author Shad.
  */

abstract class EntityGenerator(datasetConfig: Config, regexMatcher: Regex.MatchIterator) {

  final val datasetPrefix = "dataset."
  final val startPattern = "${"
  final val endPattern = "}"

  type FieldGenerator = FieldOption => String
  val fieldOptions = mutable.Map.empty[Regex, FieldOption]
  val datasetBuilder: Config => Map[String, Array[String]] = (config: Config) => {
    fieldOptions
      .filter(p => p._2.containsDataset)
      .map(x => {
        (x._2.getName, FileReader.getDataSet(config.getString(datasetPrefix +x._2.getName)))
      })
      .toMap
  }
  val fieldGenHandlers = mutable.Map.empty[String, FieldGenerator]

  lazy val dataset = datasetBuilder(datasetConfig)


  def addField(fieldName: String,
               dataType: FieldType,
               randomGen: FieldGenerator): EntityGenerator = {
    val fieldOption = dataType match {
      case StringType => (getRegex(fieldName), StringOption(getPattern(fieldName), fieldName))
      case IntType => (getRegex(fieldName), IntOption(getPattern(fieldName), fieldName))
    }
    fieldOptions += fieldOption
    fieldGenHandlers += fieldName -> randomGen
    this
  }

  def transform(template: String): String = {
    var temp = template
    fieldOptions.foreach {
      case (regex, opt) => val generator = fieldGenHandlers.get(opt.getName)
        temp = regex.replaceAllIn(temp, generator.get.apply(opt))
    }
    temp
  }

  def getRegex(str: String): Regex = {
    ("""\$\{""" + str + """\}""").r
  }

  def getPattern(s: String): String = {
    startPattern + s + endPattern
  }

}

abstract class FieldOption(pattern: String = "",
                           fieldName: String = "",
                           hasDataset: Boolean = true) {
  def getName: String = fieldName

  def containsDataset: Boolean = hasDataset
}

trait FieldType

case object StringType extends FieldType

case object IntType extends FieldType

case class IntOption(pattern: String, fieldName: String, minVal: Int = 0, maxVal: Int = 100, hasDataset: Boolean = false)
  extends FieldOption(pattern, fieldName, hasDataset)

case class StringOption(pattern: String, fieldName: String,
                        startsWith: Option[Regex] = None,
                        endsWith: Option[Regex] = None)  extends FieldOption(pattern, fieldName)

case object EmptyOption extends FieldOption






