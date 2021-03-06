package com.jetprobe.fastgen.common

import com.jetprobe.fastgen.generators.EntityGenerator
import com.typesafe.config.Config

import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import GlobalConfig._
import com.jetprobe.fastgen.generators.entity.{
  Contact,
  Location,
  PersonGen,
  RandomVal
}

/**
  * @author Shad.
  */
abstract class DatasetBuilder {
  val generators = new ListBuffer[EntityGenerator]

  var templateStr: String

  def configure(config: Option[Config], template: String): DatasetBuilder

  private def minify(s: String): String = {
    s.replaceAll("\\s", "").split('\n').map(_.trim.filter(_ >= ' ')).mkString
  }

  def generate(template: String): String = generators.foldLeft(template) {
    case (str, generator) => generator.transform(str)
  }

  def generate(count: Int)(implicit writer: Array[String] => Unit): Unit = {
    val str = minify(templateStr)
    val dataset = Array.fill[String](count)(str).par

    writer(dataset.map(generate(_) + "\n").toArray)
  }

}

object BuilderInstance {

  val builder = new DatasetBuilder {

    override def configure(config: Option[Config],
                           template: String): DatasetBuilder = {
      templateStr = template
      val regexMatches = gen findAllIn templateStr
      val mergedConfig = config match {
        case Some(config) => config.withFallback(GlobalConfig.config)
        case None         => GlobalConfig.config
      }
      generators += PersonGen(mergedConfig, regexMatches)
      generators += Location(mergedConfig, regexMatches)
      generators += Contact(mergedConfig, regexMatches)
      generators += RandomVal(mergedConfig, regexMatches)

      this
    }

    override var templateStr: String = _
  }

}
