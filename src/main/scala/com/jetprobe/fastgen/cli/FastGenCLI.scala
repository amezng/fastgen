package com.jetprobe.fastgen.cli

import com.jetprobe.fastgen.common.{FileReader, FileWriter}
import com.typesafe.scalalogging.{LazyLogging, Logger}
import org.slf4j.LoggerFactory

/**
  * @author Shad.
  */
object FastGenCLI extends App with LazyLogging {

  import com.jetprobe.fastgen.common.BuilderInstance.builder

  CmdParser.parser.parse(args, CmdConfig()) match {
    case Some(config) => {
      val recCount = config.records
      val template = config.jsonTemplate
      val output = config.out
      val t0 = System.nanoTime()
      val dataset = builder
        .configure(config.datasetConfig, FileReader.readFile(template))
        .generate(recCount)

      logger.info(
        s"Time taken to generate:  ${(System.nanoTime() - t0) / 1000000f} ms")
      val fw = FileWriter(dataset.toArray)
      fw.writeTo(output) match {
        case Some(path) => logger.info(s"Dataset generated at ${path}")
        case None       => logger.error(s"Failed to generate the dataset")
      }

      logger.debug(s"Elapsed time:  ${(System.nanoTime() - t0) / 1000000f} ms")

    }
    case None => logger.warn("Config not found.")
  }
}
