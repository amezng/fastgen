package com.jetprobe.fastgen.generators.entity

import com.jetprobe.fastgen.common.GlobalConfig
import com.jetprobe.fastgen.generators.{EntityGenerator, FieldOption, Generator, StringType}
import com.typesafe.config.Config

import scala.util.matching.Regex

/**
  * @author Shad.
  */
class Contact (datasetConfig: Config, regexMatcher: Regex.MatchIterator)
  extends EntityGenerator(datasetConfig, regexMatcher)

object Contact {

  import GlobalConfig.getRandomString

  def apply(datasetConfig: Config, regexMatcher: Regex.MatchIterator): Contact = {
    val contact = new Contact(datasetConfig, regexMatcher)

    contact.addField("Contact.email", StringType, (fieldOpt: FieldOption) => {
      getRandomString(contact.dataset,fieldOpt)
    })

    contact.addField("Contact.phone", StringType, (fieldOpt: FieldOption) => {
      getRandomString(contact.dataset,fieldOpt)
    })

    contact
  }
}
