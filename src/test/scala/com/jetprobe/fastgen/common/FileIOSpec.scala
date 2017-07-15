package com.jetprobe.fastgen.common
import java.io.File

import org.scalatest._

/**
  * @author Shad.
  */
class FileIOSpec extends FlatSpec with Matchers {

  it should "read and write from/to file" in {
    val input = Array("Hello 1", "\n", "Hello 2")
    val fw = FileWriter(input)
    var targetPath = getClass.getResource("/names.out").getFile
    var outputPath = fw.writeTo(new File(targetPath)).get
    //Handle Win environment
    if (System.getProperty("os.name").startsWith("Win")) {
      outputPath = outputPath.replaceAll("\\\\", "/")
      targetPath = targetPath.substring(1)
    }
    val output = FileReader.getDataSet(targetPath)
    outputPath should be(targetPath)
    output.length should be(input.length - 1)
    output.head should be(input.head)
  }

  it should "read the template file" in {
    val templatePath = getClass.getResource("/template-1.json").getFile
    val output = FileReader.readFile(new File(templatePath))
    output should include("Person.firstName")
    output should include("Random.UUID")
    output should include("Person.age")
  }
}
