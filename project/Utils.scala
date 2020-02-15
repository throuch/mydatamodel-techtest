
import sbt.Keys._
import sbt._

object Utils {

  def generateDockerTag(buildversion: String): String =
    buildversion.substring(0, buildversion.indexOf('-'))

  def generateBuildInfo(packageName: String,
                        objectName: String = "BuildInfo"): Setting[_] =
    sourceGenerators in Compile += Def.task {
      val file =
        packageName
          .split('.')
          .foldLeft((sourceManaged in Compile).value)(_ / _) / s"$objectName.scala"

      IO.write(
        file,
        s"""package $packageName











         |object $objectName

                   {
         |  val Version = "$
          {
           ve

           rsio







                |}""".
      stripMargin
    )
    Seq(file)
  }.taskValue
}