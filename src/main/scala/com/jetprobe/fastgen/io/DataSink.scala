package com.jetprobe.fastgen.io

import com.typesafe.scalalogging.LazyLogging

/**
  * @author Shad.
  */
trait DataSink extends LazyLogging {
  def write(data: Array[String]): Unit
}

object DataSinkBuilder {
  def apply(out: String): DataSink = out match {
    case uri if uri.startsWith("rabbit")  => RabbitMQSink(uri)
    case uri if uri.startsWith("file")    => FileSink(uri)
    case uri if uri.startsWith("mongodb") => MongoDBSink(uri)
  }
}
