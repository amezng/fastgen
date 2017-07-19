package com.jetprobe.fastgen.cli

import java.io.File

import com.typesafe.config.{Config, ConfigFactory}

/**
  * @author Shad.
  */
case class CmdConfig(out: String = ".",
                     records: Int = 1,
                     datasetConfig: Option[Config] = None,
                     jsonTemplate: File = new File("template.json"))

object CmdParser {
  val parser = new scopt.OptionParser[CmdConfig]("fastgen") {
    head("fastgen", "0.2.1")

    //Option for getting the template as a sample format. Currently only supports json
    opt[File]("template")
      .required()
      .valueName("<template>")
      .action((x, c) => c.copy(jsonTemplate = x))
      .text("Template file path.")

    //Option for saving the output
    opt[String]("out")
      .valueName("<uri>")
      .action((x, c) => c.copy(out = x))
      .text("Output URI")

    //Option for reading the dataset configuration path
    opt[File]("config")
      .valueName("<config>")
      .action(
        (x, c) => c.copy(datasetConfig = Some(ConfigFactory.parseFile(x))))
      .text("Data set configuration file path")

    //Option for number of records
    opt[Int]("rows")
      .required()
      .valueName("<# records>")
      .action((x, c) => c.copy(records = x))
      .text("Number of samples to be generated.")

    help("help").text("Prints the usage.")

    override def showUsageOnError: Boolean = true
  }

}

object Main extends App {
  println("Hello World")
}
