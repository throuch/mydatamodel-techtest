package mydatamodels.gameserver.application.http.game

import akka.actor.ActorSystem
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{complete, get, _}
import mydatamodels.core.application.http.HttpCommon

import scala.concurrent.Await

case class SearchOrders(system: ActorSystem) extends HttpCommon {

  val route =
    get {
      pathPrefix("order") {
        pathEndOrSingleSlash {

          parameters('external_ref ?, 'status ?, 'state ?, 'fromCreatedAt ?, 'toCreatedAt ?, 'fromTerminatedAt ?, 'toTerminatedAt ?) {
            (external_ref: Option[String],
             status: Option[String], state: Option[String],
             fromCreatedAt: Option[String], toCreatedAt: Option[String],
             fromTerminatedAt: Option[String], toTerminatedAt: Option[String]) =>
              completeJson(StatusCodes.OK, "{]")
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

          }
        }
      }

    }
}

