package com.jetprobe.fastgen.io

import java.io.{File, PrintWriter}

import com.typesafe.scalalogging.LazyLogging

/**
  * @author Shad.
  */
case class FileSink(filePath: String) extends DataSink {

  override def write(data: Array[String]): Unit = {
    val extractedPath = filePath.substring(5)
    FileSink.writeTo(new File(extractedPath), data) match {
      case Some(outputPath) =>
        logger.info(s"Output file saved at ${outputPath}.")
      case None => logger.error("File generation failed.")
    }

  }
}

object FileSink extends LazyLogging {

  def writeTo(f: File, data: Array[String]): Option[String] = {
    val outputPath: Option[String] = if (f.isDirectory) {
      Some(f.getAbsolutePath + "/out.json")
    } else if (f.exists()) {
      logger.warn(s"File : ${f.getName} would be overwritten")
      Some(f.getAbsolutePath)
    } else
      Some(f.getAbsolutePath)

    outputPath match {
      case Some(path) => {
        writeToFile(path, data)
        Some(path)
      }
      case None => {
        logger.warn("Unable to write. No valid output specified.")
        None
      }
    }

  }

  private def using[A <: { def close(): Unit }, B](resource: A)(f: A => B): B =
    try f(resource)
    finally resource.close()

  private def writeToFile(path: String, data: Array[String]): Unit =
    using(new PrintWriter(new File(path)))(pw => data.foreach(pw.write))
}
