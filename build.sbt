import Dependencies._
import sbt._
import Utils.generateDockerTag
import sbt.Keys.resolvers

ThisBuild / scalaVersion     := "2.12.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.mydatamodels"
ThisBuild / organizationName := "MyDataModels"

lazy val root = (project in file("."))
  .settings(
    name := "RockPaperScissors",
    libraryDependencies ++= commonDependencies ++ swaggerDependencies ++ akkaDependencies,
    resolvers ++= Seq(
      Resolver.typesafeRepo("releases"),
      Resolver.mavenLocal
    )

  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.


updateOptions := updateOptions.value.withGigahorse(false).withCachedResolution(true).withLatestSnapshots(false)


val NexusRepository = Some("spawn.thorn.consulting")
val author = "Thomas ROUCH <thomas.rouch@thorn.consulting>"
val exposedPort = Seq(9000,5559)

enablePlugins( JavaAppPackaging, AshScriptPlugin)

// sbt-native-packager docker support (official)
// Tasks: docker:stage docker:publishLocal docker:publish docker:clean
dockerEnvVars             := Map("ADVERTISED_HOST" -> "localhost", "ADVERTISED_PORT" -> exposedPort.toString)
dockerUpdateLatest        := true
dockerExposedPorts        := exposedPort
dockerBaseImage           := "openjdk:8-jre-alpine"

dockerAlias               := DockerAlias(
                                NexusRepository,
                                Option(organization.value),
                                packageName.value,
                                Option(generateDockerTag((ThisBuild / version).value)))
maintainer in Docker      := author
daemonUserUid in Docker   := None
daemonUser in Docker      := "daemon"
version in Docker         := generateDockerTag((ThisBuild / version).value)


