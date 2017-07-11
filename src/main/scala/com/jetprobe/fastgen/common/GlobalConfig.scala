package com.jetprobe.fastgen.common

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





}
