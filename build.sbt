import Dependencies._

ThisBuild / scalaVersion     := "2.12.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.mydatamodels"
ThisBuild / organizationName := "MyDataModels"

lazy val root = (project in file("."))
  .settings(
    name := "RockPaperScissors",
    libraryDependencies ++= commonDependencies ++ swaggerDependencies ++ akkaDependencies
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
