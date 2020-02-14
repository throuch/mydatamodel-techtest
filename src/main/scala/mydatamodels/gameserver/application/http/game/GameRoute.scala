package mydatamodels.gameserver.application.http.game

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives.{complete, entity, _}
import mydatamodels.core.application.http.HttpCommon
import mydatamodels.gameserver.interfaces.swagger.model
import mydatamodels.gameserver.interfaces.swagger.model.{GameAction, GameActionResponse}
import org.json4s.jackson.Serialization.write
import org.json4s.{CustomSerializer, DefaultFormats}
import akka.pattern.ask
import scala.concurrent.Await

case class GameRoute(system: ActorSystem, gameactor: ActorRef) extends HttpCommon {
  implicit val formats = DefaultFormats

  val route =
    path("play") {
      post {
        entity(as[GameAction]) { event =>
          //system.eventStream.publish( PauseEvent(event.destTaskId, event.pause))

          val status = Await.result(
            gameactor ? event,
            timeout.duration).asInstanceOf[GameActionResponse]

          //complete(HttpResponse(StatusCodes.OK, entity =model.GameActionResponse(1,"")))
          completeJson(StatusCodes.OK, write(GameActionResponse(1,"")))
        }
      }
    }
}

            //              extractRequest { request =>
            //
            //                log.info("Received SearchOrders request !")
            //
            //                val orders = Await.result(
            //                  OrderRepositoryDB.findByFilter(
            //                    OrderFilter(
            //
            //                      externalRef = external_ref,
            //                      status = status,
            //                      state = state,
            //                      createdAt = TimeRange.of(fromCreatedAt, toCreatedAt),
            //                      terminatedAt = TimeRange.of(fromTerminatedAt, toTerminatedAt))),
            //                  timeout.duration)
            //
            //                if (orders.isEmpty) {
            //                  complete(StatusCodes.NotFound)
            //                } else {
            //                  val json = write(orders)
            //                  log.debug(s"##TRO### ${json}")
            //
            //                  if (json.length == 0) {
            //                    complete(StatusCodes.InternalServerError)
            //                  } else {
            //                    completeJson(StatusCodes.OK, json)
            //                  }
            //                }
            //              }
            //          }
            //        }

