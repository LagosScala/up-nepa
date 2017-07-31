package com.lagos.scalameetup.upnepa.utility

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol


/**
 * Created by Olushola Akinmolayan on 7/24/17.
 * Default Json Formatter Utility, this can be used to convert any scala object to json
 */

trait AppJsonFormatter extends DefaultJsonProtocol with SprayJsonSupport{


}
