package mydatamodels.gameserver.application.http.game

import akka.http.scaladsl.model.StatusCodes
import mydatamodels.core.application.http.HttpCommon
import mydatamodels.gameserver.application.injection.GameApplicationMixing
import mydatamodels.gameserver.interfaces.swagger.game.GameAPI

/**
 *
 * Empty Implementation for now
 *
 *
 */
class Reset(implicit appContext: GameApplicationMixing) extends HttpCommon with GameAPI {

  val route = reset

  override def reset =
    get {
      pathPrefix("reset") {
        pathEndOrSingleSlash {

          appContext.resetDefaultMatch()

          complete(StatusCodes.OK)

        }

      }

    }
}