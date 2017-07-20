package com.lagos.scalameetup.upnepa.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}


class BaseRoutesSpec extends WordSpec with Matchers with ScalatestRouteTest with BaseRoutes{

  "BaseRoute" should {
    "answer to any request to `/`" in {
      Get("/") ~> route ~> check{
        status shouldBe StatusCodes.OK
        responseAs[String] shouldBe "Hello from up-nepa chatbot server!"
      }
    }
    "not handle a POST request to `/`" in {
      Post("/") ~> route ~> check {
        handled shouldBe false
      }
    }
  }

}
