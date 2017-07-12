package com.jetprobe.fastgen.generators.entity

import com.jetprobe.fastgen.common.GlobalConfig
import com.jetprobe.fastgen.generators._
import com.typesafe.config.Config

import scala.util.Random
import scala.util.matching.Regex

/**
  * @author Shad.
  */
class Location(datasetConfig: Config, regexMatcher: Regex.MatchIterator)
  extends EntityGenerator(datasetConfig, regexMatcher)

object Location {

  import GlobalConfig.getRandomString

  def apply(datasetConfig: Config, regexMatcher: Regex.MatchIterator): Location = {
    val address = new Location(datasetConfig, regexMatcher)

    address.addField("Location.city", StringType, (fieldOpt: FieldOption) => {
      getRandomString(address.dataset,fieldOpt)
    })

    address.addField("Location.country", StringType, (fieldOpt: FieldOption) => {
      getRandomString(address.dataset,fieldOpt)
    })

    address.addField("Location.street", StringType, (fieldOpt: FieldOption) => {
      getRandomString(address.dataset,fieldOpt)
    })

    address.addField("Location.zipCode", StringType, (fieldOpt: FieldOption) => {
      getRandomString(address.dataset,fieldOpt)
    })




    address
  }
}