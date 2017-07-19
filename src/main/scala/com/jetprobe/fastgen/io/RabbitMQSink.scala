package com.jetprobe.fastgen.io

import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory

/**
  * @author Shad.
  */
case class RabbitMQSink(host: String,
                        vHost: Option[String] = None,
                        exchange: String,
                        username: String,
                        password: String,
                        routingKey: String)
    extends DataSink {

  private lazy val connection =
    RabbitMQSink.getConnection(host, username, password, vHost)
  private lazy val channel = connection.createChannel()

  override def write(data: Array[String]): Unit = {
    if (!channel.isOpen)
      channel.exchangeDeclare(exchange, "topic", true)
    data.foreach(message => {
      channel.basicPublish(exchange, routingKey, null, message.getBytes)
    })
    channel.close()
    channel.abort()
    connection.close()
  }
}

object RabbitMQSink {
  //config properties
  private val connection: Connection = null

  def apply(rabbitURI: String): RabbitMQSink = {
    val rabbitOptions = rabbitURI.substring(9).split("\\/")
    val host = rabbitOptions(0)
    val (vHost, exchange) =
      if (rabbitOptions.length > 2)
        (Some(rabbitOptions(1)), rabbitOptions(2).split("\\?")(0))
      else
        (None, rabbitOptions(1).split("\\?")(0))

    val extraOpts = rabbitURI.split("\\?")
    val username = extraOpts(1).split("=")(1)
    val password = extraOpts(2).split("=")(1)
    val routingKey = extraOpts(3).split("=")(1)
    RabbitMQSink(host, vHost, exchange, username, password, routingKey)
  }

  def getConnection(host: String,
                    username: String,
                    password: String,
                    vHost: Option[String]): Connection = {
    connection match {
      case null => {
        val factory = new ConnectionFactory()
        factory.setHost(host)
        factory.setUsername(username)
        factory.setPassword(password)
        if (vHost.nonEmpty)
          factory.setVirtualHost(vHost.get)
        factory.newConnection()
      }
      case _ => connection
    }
  }
}
