package mydatamodels.core.application.http.common

import akka.actor.ActorSystem
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.OK
import akka.http.scaladsl.server.Directives.{complete, get, path}

class Ping {
  val route = path("ping") {
    get {
      complete(HttpResponse(OK, entity = "pong"))
    }
  }
}


