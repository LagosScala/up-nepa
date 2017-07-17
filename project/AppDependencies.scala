import sbt._

object AppDependencies {
  private val akkaVersion = "2.5.3"
  private val akkaHttpVersion = "10.0.9"
  private val akka = "com.typesafe.akka" %% "akka-actor" %  akkaVersion
  private val akkaStream = "com.typesafe.akka" %% "akka-stream" % akkaVersion
  private val akkaHttp = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
  private val akkaHttpJson = "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion
  private val akkaLogging = "com.typesafe.akka" %% "akka-slf4j" % akkaVersion
  private val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"

  val upnepaDependencies: Seq[ModuleID] =  Seq(
    akka, akkaStream, akkaHttp, akkaHttpJson, akkaLogging, logback
  )
}