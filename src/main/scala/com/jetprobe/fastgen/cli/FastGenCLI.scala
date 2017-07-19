package com.jetprobe.fastgen.cli

import com.jetprobe.fastgen.common.{FileReader, FileWriter}
import com.jetprobe.fastgen.io.{DataSink, DataSinkBuilder}
import com.typesafe.scalalogging.{LazyLogging, Logger}
import org.slf4j.LoggerFactory

import scala.collection.mutable.ArrayBuffer

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
        .generate(recCount) { data =>
          DataSinkBuilder(output).write(data)
        }

      logger.info(
        s"Time taken to generate:  ${(System.nanoTime() - t0) / 1000000f} ms")
    }
    case None => logger.warn("Config not found.")
  }
}
