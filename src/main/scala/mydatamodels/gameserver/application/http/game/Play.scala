package mydatamodels.gameserver.application.http.game

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives
import akka.http.scaladsl.server.Directives.{complete, entity, _}
import akka.pattern.ask
import mydatamodels.core.application.http.HttpCommon
import mydatamodels.gameserver.interfaces.swagger.converter.JsonSupport
import mydatamodels.gameserver.interfaces.swagger.game.GameAPI
import mydatamodels.gameserver.interfaces.swagger.model.{GameAction, GameActionResponse}
//import org.json4s.DefaultFormats

import scala.concurrent.Await

case class Play(system: ActorSystem, gameactor: ActorRef) extends HttpCommon with GameAPI with JsonSupport {

  implicit val requestFormat = jsonFormat1(GameAction)
  implicit val responseFormat = jsonFormat2(GameActionResponse)

  val route = play


  override def play =
    path("play") {
      post {
        entity(as[GameAction]) { event =>

          val status = Await.result(
            (gameactor ? event).mapTo[GameActionResponse],
            timeout.duration)


          complete(if (status.humanWins) StatusCodes.OK else StatusCodes.ImATeapot, status.message)


          //          complete {
          //            (gameactor ? event).mapTo[GameActionResponse]
          //          }


        }
      }
    }
}

