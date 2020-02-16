package mydatamodels.gameserver.application.http.game

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import mydatamodels.core.application.http.HttpCommon
import mydatamodels.gameserver.interfaces.swagger.converter.JsonSupport
import mydatamodels.gameserver.interfaces.swagger.model.{GameAction, GameActionResponse}

import scala.concurrent.ExecutionContext
import akka.pattern.ask
import mydatamodels.gameserver.application.GameApp
import mydatamodels.rps.interfaces.RPSElement

class Play(system: ActorSystem, gameactor: ActorRef)(implicit executionContext: ExecutionContext) extends HttpCommon with JsonSupport {

  implicit val element = enumFormat(RPSElement)
  implicit val requestFormat = jsonFormat1(GameAction)
  implicit val responseFormat = jsonFormat2(GameActionResponse)

  val route = play

  def play =
    path("play") {
      post {
        entity(as[GameAction]) { event =>

          complete {
            (gameactor ? (GameApp.matchID, event)).mapTo[GameActionResponse].
              map(status ⇒ HttpResponse(if (status.humanWins) StatusCodes.OK else StatusCodes.ImATeapot,
                entity = status.message))
          }
        }
      }
    }
}

