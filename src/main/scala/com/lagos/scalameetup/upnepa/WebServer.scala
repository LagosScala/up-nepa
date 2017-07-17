/**
  * Created by Babatunde Abdulquddus on 02/07/17.
  */

package com.lagos.scalameetup.upnepa

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import spray.json.PrettyPrinter

case class Message(text: String)

object WebServer extends App {
  /**
    * Set up needed to provide resources (actor system, actor materializer, and execution context for futures)
    * used internally by akka http
    */
  implicit val system = ActorSystem("upnepa-chatbot-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  /**
    * For marshalling and unmarshalling json to and from scala objects (Message objects)
    */
  implicit val printer = PrettyPrinter
  implicit val messageFormat = jsonFormat1(Message)

  /**
    * Used to read configuration data from .conf files
    */
  val config = ConfigFactory.load()
  val host =  config.getString("http.host")
  val port =  config.getInt("http.port")

  /**
    * http endpoints routes
    */
  val route =
    pathEndOrSingleSlash {
      get {
        complete(Message("Hello from up-nepa chatbot server!"))
      }
    }

  /**
    * Used for logging messages to the console
    */
  val logger =  Logging(system.eventStream, "up-nepa")

  /**
    * Starts http server and binds it to a host and port
    */
  val bindingFuture = Http().bindAndHandle(route, host, port)

  logger.info(s"Server started at http://localhost:$port... \n")
}