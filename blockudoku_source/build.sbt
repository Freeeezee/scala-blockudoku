ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.3"

lazy val root = (project in file("."))
  .settings(
    name := "blockudoku_source"
  )

libraryDependencies += "org.jline" % "jline" % "3.26.2"

libraryDependencies += "com.github.sbt" % "junit-interface" % "0.13.3" % "test"