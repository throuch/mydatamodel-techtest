package mydatamodels.gameserver.application.http.game

import akka.actor.ActorSystem
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import mydatamodels.core.application.http.HttpCommon
import mydatamodels.gameserver.application.injection.Module

case class Reset(system: ActorSystem) extends HttpCommon {


  val route =
    get {
      pathPrefix("reset") {
        pathEndOrSingleSlash {

          Module.DefaultMatchRecorder.resetResults
          complete(StatusCodes.OK)

        }

      }

    }
}