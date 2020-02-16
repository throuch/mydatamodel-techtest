package mydatamodels.gameserver.application.http.game

import akka.actor.ActorRef
import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.pattern.ask
import mydatamodels.core.application.http.HttpCommon
import mydatamodels.gameserver.application.injection.{GameApplicationMixing}
import mydatamodels.gameserver.interfaces.swagger.converter.JsonSupport
import mydatamodels.gameserver.interfaces.swagger.model.{GameAction, GameActionResponse}
import mydatamodels.rps.interfaces.RPSElement

import scala.concurrent.ExecutionContext

class Play(gameactor: ActorRef)(implicit val ec: ExecutionContext, appContext: GameApplicationMixing) extends HttpCommon with JsonSupport {


  implicit val element = enumFormat(RPSElement)
  implicit val requestFormat = jsonFormat1(GameAction)
  implicit val responseFormat = jsonFormat2(GameActionResponse)

  val route = play

  def play =
    path("play") {
      post {
        entity(as[GameAction]) { event =>

          val match_id = appContext.getDefaultMatch() // STUB
          complete {
            (gameactor ? (match_id, event)).mapTo[GameActionResponse].
              map(status ⇒ HttpResponse(if (status.humanWins) StatusCodes.OK else StatusCodes.ImATeapot,
                entity = status.message))
          }
        }
      }
    }
}

