package com.jetprobe.fastgen.generators.entity

import com.jetprobe.fastgen.generators._
import com.typesafe.config.Config

import scala.util.Random
import scala.util.matching.Regex

/**
  * @author Shad.
  */
class PersonGen(datasetConfig: Config, regexMatcher: Regex.MatchIterator)
  extends EntityGenerator(datasetConfig, regexMatcher)

object PersonGen {

  def apply(datasetConfig: Config, regexMatcher: Regex.MatchIterator): PersonGen = {
    val person = new PersonGen(datasetConfig, regexMatcher)

    // firstName
    person.addField("Person.firstName", StringType, (fieldOpt: FieldOption) => {
      val arr = person.dataset.get(fieldOpt.getName).get
      Generator.getRNG(arr.length, arr)
    })

    //lastName
    person.addField("Person.lastName", StringType, (fieldOpt: FieldOption) => {
      val arr = person.dataset.get(fieldOpt.getName).get
      Generator.getRNG(arr.length, arr)
    })

    //Support for parsing and generating age
    person.addField("Person.age", IntType, (fieldOpt: FieldOption) => {
      val randomInt = fieldOpt match {
        case opt: IntOption => opt.minVal + Random.nextInt((opt.maxVal - opt.minVal) + 1)
        case _ => Random.nextInt(100)
      }
      randomInt.toString
    })

    person

  }

}