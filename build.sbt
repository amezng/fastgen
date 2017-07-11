name := "fastgen"

version := "0.1.0"

scalaVersion := "2.11.8"


libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.1",
  "com.github.scopt" %% "scopt" % "3.6.0",
  "org.typelevel" %% "cats" % "0.9.0",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.1",
  "org.slf4j" % "slf4j-simple" % "1.7.25"
  )

mainClass in assembly := Some("com.jetprobe.fastgen.cli.GenFastCLI")
