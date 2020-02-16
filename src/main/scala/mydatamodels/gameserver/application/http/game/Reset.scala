package mydatamodels.gameserver.application.http.game

import akka.actor.ActorSystem
import akka.http.scaladsl.model.StatusCodes
import mydatamodels.core.application.http.HttpCommon
import mydatamodels.gameserver.application.injection.Module
import mydatamodels.gameserver.interfaces.swagger.game.GameAPI

/**
 *
 * Empty Implementation for now
 *
 *
 */
class Reset extends HttpCommon with GameAPI {

  val route = reset

  override def reset =
    get {
      pathPrefix("reset") {
        pathEndOrSingleSlash {

          Module.DefaultGameService.resetDefaultMatch()

          complete(StatusCodes.OK)

        }

      }

    }
}