package mydatamodels.gameserver.application.http.game

import akka.actor.ActorSystem
import akka.http.scaladsl.model.StatusCodes
import mydatamodels.core.application.http.HttpCommon
import mydatamodels.core.infrastructure.InMemoryMatchRepository
import mydatamodels.gameserver.application.GameApp
import mydatamodels.gameserver.application.injection.Module
import mydatamodels.gameserver.interfaces.swagger.game.GameAPI

class Reset(system: ActorSystem) extends HttpCommon with GameAPI {

  val route = reset

  override def reset =
    get {
      pathPrefix("reset") {
        pathEndOrSingleSlash {


          InMemoryMatchRepository.delete(GameApp.matchID)
          complete(StatusCodes.OK)

        }

      }

    }
}