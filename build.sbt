name := "snappy_test_sbt"

version := "1.0"

scalaVersion := "2.11.7"

resolvers ++= Seq("conjars.org" at "http://conjars.org/repo")

libraryDependencies ++= Seq(
  "io.snappydata" % "snappy-core_2.10" % "0.1.0-PREVIEW"
)