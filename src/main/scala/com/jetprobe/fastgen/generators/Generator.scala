package com.jetprobe.fastgen.generators

import scala.util.Random

/**
  * @author Shad.
  */
package entities {

  private[entities] class Person
}
object Generator {

  def getRNG(maxRange: Int, dataSet: Array[String]): String = {
    val rand = new Random().nextInt(maxRange)
    dataSet(rand)
  }


}


