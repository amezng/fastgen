package com.jetprobe.fastgen.common

import com.jetprobe.fastgen.generators.{FieldOption, Generator}
import com.typesafe.config.ConfigFactory

/**
  * @author Shad.
  */
object GlobalConfig {

  lazy val config = ConfigFactory.load("genfast")
  lazy val gen = """\$\{([a-zA-Z])\w+\.?([a-zA-Z])\w+\}""".r
  val startPattern = "${"
  val endPattern = "}"

  //val personFirstNames = config.getString("default.dataset.person.firstName")
  //val personLastNames =  config.getString("default.dataset.person.surnames")

 // val defFirstName = config.getString("dataset.Person.firstName")
 // val defLastName = config.getString("dataset.Person.Name")


  def getRandomString(dataset : Map[String,Array[String]], fieldOpt : FieldOption): String ={
    dataset.get(fieldOpt.getName) match {
      case Some(arr) => Generator.getRNG(arr.length, arr)
      case None => fieldOpt.getName
    }
  }


}
