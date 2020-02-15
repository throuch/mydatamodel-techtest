
package mydatamodels.gameserver.application.http


import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.{RejectionHandler, Route}
import akka.stream.ActorMaterializer
import ch.megard.akka.http.cors.scaladsl.CorsDirectives.cors
import mydatamodels.core.application.http.common.Site
import mydatamodels.gameserver.application.http.game.{GetResults, Play, Reset}
import mydatamodels.gameserver.interfaces.swagger.SwaggerDocService
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

class GameHttpServer(game: ActorRef)(implicit val system: ActorSystem) extends Site {
  val log = LoggerFactory.getLogger(getClass)

  implicit def myRejectionHandler =
    RejectionHandler.newBuilder()
      .handle {
        case _ => {
          complete(HttpResponse(StatusCodes.Forbidden, entity = "Invalid body !"))
        }
      }
      .handleNotFound {
        complete(HttpResponse(StatusCodes.InternalServerError))
      }
      .result()


  implicit val ec: ExecutionContext = system.dispatcher
  implicit val m: ActorMaterializer = ActorMaterializer()


  val route =
    cors()(
      Route.seal(
        SwaggerDocService.routes ~
          Play(system, game).route ~
          GetResults(system).route ~
          Reset(system).route ~
          site)
    )

  val port = sys.env("ADVERTISED_PORT").toShort

  val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", port)

  bindingFuture.
    onComplete {
      case Success(bound) =>
        log.info(s"Server online at http://${bound.localAddress.getHostString}:${bound.localAddress.getPort}/")
      case Failure(e) =>
        log.error(s"Server could not start!", e)
        system.terminate()
    }


}

