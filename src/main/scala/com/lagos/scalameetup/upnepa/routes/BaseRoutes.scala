package com.lagos.scalameetup.upnepa.routes

import akka.actor.Status
import akka.http.scaladsl.server.Directives._
import spray.json.DefaultJsonProtocol._
import spray.json.PrettyPrinter

/**
 * Created by olushola on 7/19/17.
 * Separating routes for easy readability
 */
trait BaseRoutes {

  case class Message(text: String){
    require(!text.matches("[a-zA-Z ]{1,})(\\\\d+)([a-zA-Z ]{1,}"),"Invalid message")
  }

  /**
   * For marshalling and unmarshalling json to and from scala objects (Message objects)
   */
  implicit val printer = PrettyPrinter
  implicit val messageFormat = jsonFormat1(Message)

  // val gPSLocationFinderActor =
  /**
   * http endpoints routes
   */
  val route =
    pathEndOrSingleSlash {
      get {
        complete("Hello from up-nepa chatbot server!")
      }
    }


}
