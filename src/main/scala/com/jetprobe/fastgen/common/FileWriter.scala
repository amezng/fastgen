package com.jetprobe.fastgen.common

import java.io.{File, PrintWriter}

import com.typesafe.scalalogging.LazyLogging

/**
  * @author Shad.
  */
case class FileWriter(data: Array[String]) extends LazyLogging {

  /**
    * Write the data to a specific path
    * @param f File to write the output
    * @return the file path where the output was written
    */
  def writeTo(f: File): Option[String] = {
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
