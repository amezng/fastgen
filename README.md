# fastgen [![Build Status](https://travis-ci.org/amezng/fastgen.svg?branch=master)](https://travis-ci.org/amezng/fastgen)
A tool for generating random data

### How to build ?
1. Get the latest code.
`git clone https://github.com/amezng/fastgen.git`
2. Make sure you have installed [sbt](http://www.scala-sbt.org/).
3. Navigate to the directory `fastgen` and trigger `sbt assembly` command. After this step, you should be able to create the runnable jar, which will be stored in `<project-folder>/target/scala-2.11`

### How to run the project ?
A detailed documentation can be found in the [wiki](https://github.com/amezng/fastgen/wiki)

### Where do I find the executable jars ?
Check out the [releases page](https://github.com/amezng/fastgen/releases)

### What's coming up 
-[x] Support for Location and Random String
-[ ] Support for custom options based on regex for generating strings like ${Person.firstName[^'Sha']}
-[ ] Support for Mongo DB as the output destination
-[ ] Support for Kafka as the output destination
-[ ] Support for RabbitMQ as the output  destination
-[ ] Support for batch based ingestion, with configurable batch size and interval.