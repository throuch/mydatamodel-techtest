
package mydatamodels.gameserver.application.http


import com.sun.corba.se.impl.presentation.rmi.ExceptionHandler

import io.swagger.annotations._
import javax.ws.rs.{Consumes, Produces}
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}
import io.swagger.annotations._
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

@Api(value = "/play")
@Consumes(value = Array("application/json", "application/json"))
@Produces(value = Array("application/json", "application/json"))
class OrderManagerHttpServer(implicit val system : ActorSystem) extends Site {
  val log = LoggerFactory.getLogger(getClass)
  
  implicit def myExceptionHandler: ExceptionHandler =
    ExceptionHandler {
      case _: ArithmeticException =>
        extractUri { uri =>
          println(s"Request to $uri could not be handled normally")
          complete(HttpResponse(StatusCodes.InternalServerError, entity = "Bad numbers, bad result!!!"))
        }
      case _: java.util.NoSuchElementException =>
      {
        complete(HttpResponse(StatusCodes.NotFound, entity = "Element requested not found"))
      }
      
    }
  
  implicit def myRejectionHandler =
    RejectionHandler.newBuilder()
      .handle {
        case UnsupportedRequestContentTypeRejection(_) => {
          complete(HttpResponse(StatusCodes.BadRequest, entity = "Invalid body !"))
        }
      }
      .handleNotFound { complete(HttpResponse(StatusCodes.InternalServerError)) }
      .result()
  
  
  implicit val  ec: ExecutionContext = system.dispatcher
  implicit val  m: ActorMaterializer = ActorMaterializer()

  
  val route =
    cors() (

        Route.seal(
          SwaggerDocService.routes ~
          Ping(system).route ~
          Status(system).route ~
          PauseTask(system).route ~
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

