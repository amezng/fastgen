package com.jetprobe.fastgen.cli

import java.io.File

import com.typesafe.config.{Config, ConfigFactory}

/**
  * @author Shad.
  */
case class CmdConfig(out: File = new File("."), records: Int = 1, datasetConfig: Option[Config] = None, jsonTemplate: File = new File("template.json"))

object CmdParser {
  val parser = new scopt.OptionParser[CmdConfig]("GenFast") {
    head("FastGen", "0.1.0")

    //Option for getting the template as a sample format. Currently only supports json
    opt[File]("template").required().valueName("<json-template>").
      action((x, c) => c.copy(jsonTemplate = x)).
      text("Json template file path")

    //Option for saving the output
    opt[File]("out").valueName("<output-file>").
      action((x, c) => c.copy(out = x)).
      text("output path is a required file property")

    //Option for reading the dataset configuration path
    opt[File]("config").valueName("<dataset-config>").
      action((x, c) => c.copy(datasetConfig = Some(ConfigFactory.parseFile(x)))).
      text("Dataset configuration file path")

    //Option for number of records
    opt[Int]("rows").required().valueName("<Number of records>")
      .action((x, c) => c.copy(records = x)).text("Number of samples to be generated")

    help("help").text("prints this usage text")

    override def showUsageOnError: Boolean = true
  }


}