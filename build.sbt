name := "fastgen"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.8"


libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.1",
  "com.github.scopt" %% "scopt" % "3.6.0",
  "org.typelevel" %% "cats" % "0.9.0",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.1",
  "org.slf4j" % "slf4j-simple" % "1.7.25"
  //"org.apache.logging.log4j" %% "log4j-api-scala" % "2.8.2",
 // "org.apache.logging.log4j" % "log4j-api" % "2.8.2",
  //"org.apache.logging.log4j" % "log4j-core" % "2.8.2" % "runtime"

)

mainClass in assembly := Some("org.jetprobe.genfast.cli.GenFastCLI")
