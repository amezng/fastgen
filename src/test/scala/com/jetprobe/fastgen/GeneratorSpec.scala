package com.jetprobe.fastgen

import com.jetprobe.fastgen.generators.Generator
import org.scalatest._

import scala.collection.mutable

/**
  * @author Shad.
  */
class GeneratorSpec extends FlatSpec with Matchers {

  it should "throw exception on negative max range" in {
    val obj = Generator
    a[IllegalArgumentException] should be thrownBy {
      obj.getRNG(-1, Array("Shad", "Amez"))
    }
  }

}
