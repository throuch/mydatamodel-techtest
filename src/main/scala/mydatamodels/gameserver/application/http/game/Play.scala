package mydatamodels.gameserver.application.http.game

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives.{complete, entity, _}
import mydatamodels.core.application.http.HttpCommon
import mydatamodels.gameserver.interfaces.swagger.model
import mydatamodels.gameserver.interfaces.swagger.model.{GameAction, GameActionResponse}
import org.json4s.jackson.Serialization.write
import org.json4s.{CustomSerializer, DefaultFormats}
import akka.pattern.ask
import mydatamodels.gameserver.interfaces.swagger.converter.JsonSupport

import scala.concurrent.Await

case class Play(system: ActorSystem, gameactor: ActorRef) extends HttpCommon with JsonSupport {
  implicit val formats = DefaultFormats

  val route =
    path("play") {
      post {
        entity(as[GameAction]) { event =>

          val status = Await.result(
            gameactor ? event,
            timeout.duration).asInstanceOf[GameActionResponse]


            complete(if (status.humanWins) StatusCodes.OK else StatusCodes.ImATeapot, status.message)


        }
      }
    }
}

