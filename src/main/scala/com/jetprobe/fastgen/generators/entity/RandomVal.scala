package com.jetprobe.fastgen.generators.entity

import java.security.SecureRandom

import com.jetprobe.fastgen.generators.{
  EntityGenerator,
  FieldOption,
  StringType
}
import com.typesafe.config.Config

import scala.util.matching.Regex

/**
  * @author Shad.
  */
class RandomVal(datasetConfig: Config, regexMatcher: Regex.MatchIterator)
    extends EntityGenerator(datasetConfig, regexMatcher)

object RandomVal {
  val abc = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
  val numberStr = "0123456789"
  lazy val rnd = new SecureRandom()

  def apply(datasetConfig: Config,
            regexMatcher: Regex.MatchIterator): RandomVal = {
    val random = new RandomVal(datasetConfig, regexMatcher)

    random.addField("Random.String", StringType, (fieldOpt: FieldOption) => {
      val sb = new StringBuilder(10)
      for (i <- 0 until 20) {
        sb.append(abc(rnd.nextInt(abc.length)))
      }
      sb.toString
    })

    random.addField(
      "Random.number",
      StringType,
      (fieldOpt: FieldOption) => {
        val sb = new StringBuilder(10)
        for (i <- 0 until 10) {
          sb.append(abc(rnd.nextInt(numberStr.length)))
        }
        sb.toString
      }
    )

    random
  }
}
