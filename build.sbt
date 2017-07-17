import AppDependencies._
import sbt.Keys._

lazy val root = (project in file(".")).enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "up-nepa",
    version := "1.0",
    organization := "com.lagos.scalameetup.upnepa",
    incOptions := incOptions.value.withNameHashing(true),
    offline := true
  )
  .settings(libraryDependencies ++= upnepaDependencies)
  .settings(
    mainClass in assembly := Some("com.lagos.scalameetup.upnepa.WebServer"),
    assemblyJarName in assembly := "com.lagos.scalameetup.upnepa.jar"
  )