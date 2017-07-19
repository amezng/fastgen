package com.jetprobe.fastgen.io

import org.mongodb.scala.bson.BsonDocument
import org.mongodb.scala.{Completed, MongoClient, MongoCollection, Observer}
import org.mongodb.scala.bson.collection.mutable.Document
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

/**
  * @author Shad.
  */
case class MongoDBSink(db: String, collection: String, host: String)
    extends DataSink {

  import MongoDBSink._

  override def write(data: Array[String]): Unit = {
    val collection = getCollection(this)

    data
      .grouped(1000)
      .foreach(docs => {
        val observable =
          collection.insertMany(docs.map(str => Document(BsonDocument(str))))
        Await.result(observable.head(), 10 seconds)
      })
    logger.info(s"Total docs inserted : ${data.length}")
  }

}

object MongoDBSink {

  import org.json4s._
  import org.json4s.jackson.JsonMethods._

  def apply(uri: String): MongoDBSink = {
    val splitUri = uri.substring(10).split("/")
    val hostname = "mongodb://" + splitUri(0)
    val database = splitUri(1)
    val collection = splitUri(2).split("\\?")(0)
    MongoDBSink(database, collection, hostname)
  }

  def getCollection(mongo: MongoDBSink): MongoCollection[Document] = {
    val mongoClient = MongoClient(mongo.host)
    mongoClient.getDatabase(mongo.db).getCollection(mongo.collection)
  }

  def jsonStrToMap(jsonStr: String): Map[String, Any] = {
    implicit val formats = org.json4s.DefaultFormats
    parse(jsonStr).extract[Map[String, Any]]
  }
}
