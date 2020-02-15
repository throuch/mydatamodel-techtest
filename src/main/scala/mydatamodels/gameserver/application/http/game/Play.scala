package mydatamodels.gameserver.application.http.game

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{complete, entity, _}
import akka.pattern.ask
import mydatamodels.core.application.http.HttpCommon
import mydatamodels.gameserver.interfaces.swagger.converter.JsonSupport
import mydatamodels.gameserver.interfaces.swagger.model.{GameAction, GameActionResponse}
import org.json4s.DefaultFormats

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

