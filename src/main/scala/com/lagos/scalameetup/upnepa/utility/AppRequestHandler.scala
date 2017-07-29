package com.lagos.scalameetup.upnepa.utility

import akka.http.scaladsl.model._
import scala.collection.immutable.Seq
import scala.concurrent.Future
import akka.http.scaladsl.model.HttpMethods._


trait AppRequestHandler {

  def sendRequest(method : String, endpoint : String): Unit ={

    if(method.equalsIgnoreCase("get")){
      val request = HttpRequest(GET, uri = endpoint)
      print(request)

    }

  }


}
