package com.lagos.scalameetup.upnepa.services

import akka.actor.{Actor, ActorLogging, ActorSystem}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.stream.ActorMaterializer
import akka.util.ByteString
import spray.json._
import DefaultJsonProtocol._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.http.scaladsl.settings.{ClientConnectionSettings, ConnectionPoolSettings}

import scala.util.{Failure, Success}
import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}
import com.softwaremill.sttp._

import scala.collection.immutable

case class GeoLocation( lat: Double , lng: Double )
case class Location(name: String)

/*class GPSLocationFinderActor(implicit  val system: ActorSystem , implicit  val mat : ActorMaterializer) extends Actor with ActorLogging {

  val http = Http(context.system)
  implicit val dispatcher = system.dispatcher

  override def receive: Receive = {
    case location @ Location(name) =>


           http.singleRequest(HttpRequest(uri ="https://maps.googleapis.com/maps/api/geocode/json?address=court+road+kano"))
             .map(_.entity.dataBytes.runFold(ByteString(""))(_ ++ _ ).foreach{ body =>
                 // log.info(body.utf8String)
                 val responseAst = body.utf8String.parseJson
                 val responseObject = responseAst.asJsObject.getFields("results")(0).asInstanceOf[JsArray]
                  println(responseObject)
             })
  }

   def responseMarshaller()= ???
}*/


class GPSLocationService(implicit  val system: ActorSystem , implicit  val mat : ActorMaterializer, implicit val ex: ExecutionContext) {

  implicit val json4Geolocation = jsonFormat2(GeoLocation)

  /***
    *
    * @param location string
    * @return Option[GeoLocation]
    */

  def findLocation(location: String): Option[GeoLocation] = {

    implicit val handler = HttpURLConnectionSttpHandler

    val query = location.replaceAll(" ", "+")

    val request =sttp.get(uri"https://maps.googleapis.com/maps/api/geocode/json?address=$query")
    val response = request.send()

    val result = response.body.parseJson.asJsObject.getFields("results")

    val responseObject = result.map(_.asInstanceOf[JsArray].elements).filter(_.nonEmpty).flatten

      if(responseObject.nonEmpty) {

          responseObject.head.asJsObject.getFields("geometry")
          .flatMap(_.asJsObject.getFields("location"))
          .headOption.map(_.convertTo[GeoLocation])
      }
      else None
  }


}



class GPSLocationRoute(gps: GPSLocationService){

import gps._
  val route: Route = {
    path("location" / Segment ){ location =>
      get{
        complete {
         gps.findLocation(location).toJson.prettyPrint
        }
      }
    }
  }

}





