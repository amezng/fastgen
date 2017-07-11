package com.jetprobe.fastgen.generators

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
  * @author Shad.
  */

object Generator {

  def getRNG(maxRange: Int, dataSet: Array[String]): String = {
    val rand = new Random().nextInt(maxRange)
    dataSet(rand)
  }


  }


