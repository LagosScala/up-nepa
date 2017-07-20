package com.lagos.scalameetup.upnepa.config

import com.typesafe.config.ConfigFactory

/**
 * Created by olushola on 7/19/17.
 * Application configuration separated for easy debugging and also to follow Single Responsibilty Principle
 * Whatsoever is defined after the config declaration reads from the application.conf file inside of resource directory
 */
trait ApplicationConfiguration{

  val config = ConfigFactory.load()
  val host =  config.getString("http.host")
  val port =  config.getInt("http.port")

}
