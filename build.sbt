name := "fastgen"

version := "0.2.1"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.1",
  "com.github.scopt" %% "scopt" % "3.6.0",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.1",
  "org.slf4j" % "slf4j-simple" % "1.7.25",
  "com.rabbitmq" % "amqp-client" % "3.6.5",
  "org.mongodb.scala" %% "mongo-scala-driver" % "2.1.0",
  "org.json4s" %% "json4s-native" % "3.5.2",
  "org.json4s" %% "json4s-jackson" % "3.5.2",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)

mainClass in assembly := Some("com.jetprobe.fastgen.cli.FastGenCLI")

assemblyJarName in assembly := name.value + "-" + version.value + ".jar"

resolvers += "New Motion Repository" at "http://nexus.thenewmotion.com/content/groups/public/"
