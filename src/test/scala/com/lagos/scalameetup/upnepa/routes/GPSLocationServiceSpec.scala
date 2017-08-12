package com.lagos.scalameetup.upnepa.routes


import com.lagos.scalameetup.upnepa.services.{ GPSLocationService, GeoLocation}
import org.scalatest.{Matchers, WordSpec}


class GPSLocationServiceSpec extends  WordSpec
           with Matchers {


     "GPSLocationService" should {
         "response with null to request `/`location/" in  {
           val gps = new GPSLocationService()
              assert(gps.findLocation(" ").isEmpty )
         }

          "response with GeoLocation()  `/location/'court road kano nigeria" in {
              val gps = new GPSLocationService()
               assert(gps.findLocation("court road kano nigeria").contains(GeoLocation(12.0213213, 8.534578699999999)))
          }
     }
}
