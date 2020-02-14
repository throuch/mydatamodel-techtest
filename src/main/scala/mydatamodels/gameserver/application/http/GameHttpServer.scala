
package mydatamodels.gameserver.application.http


import akka.actor.ActorSystem
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.server
import akka.http.scaladsl.server.{ExceptionHandler, RejectionHandler, Route, UnsupportedRequestContentTypeRejection}
import io.swagger.annotations._
import javax.ws.rs.{Consumes, Produces}
import org.slf4j.LoggerFactory
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes.NotFound
import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.stream.ActorMaterializer
import ch.megard.akka.http.cors.scaladsl.CorsDirectives.cors
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{complete, _}
import mydatamodels.core.application.http.common.Site
import mydatamodels.gameserver.application.http.game.{Play, Reset, GetResults}
import mydatamodels.gameserver.interfaces.swagger.SwaggerDocService
import mydatamodels.rps.application.actors.GameActor
import mydatamodels.rps.domain.ClassicGame

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

class GameHttpServer(game: ClassicGame)(implicit val system: ActorSystem) extends Site {
  val log = LoggerFactory.getLogger(getClass)
  val gameActorRef = system.actorOf(GameActor.props(game), "GameActor")

  /*implicit def myExceptionHandler: ExceptionHandler =
    server.ExceptionHandler {
      case _: ArithmeticException =>
        extractUri { uri =>
          println(s"Request to $uri could not be handled normally")
          complete(HttpResponse(StatusCodes.InternalServerError, entity = "Bad numbers, bad result!!!"))
        }
      case _: java.util.NoSuchElementException => {
        complete(HttpResponse(StatusCodes.NotFound, entity = "Element requested not found"))
      }

    }
*/
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
          Play(system, gameActorRef).route ~
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

