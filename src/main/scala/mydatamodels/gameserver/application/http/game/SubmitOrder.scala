package mydatamodels.gameserver.application.http.game

import java.net.URL

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.pattern.ask
import mydatamodels.core.application.http.HttpCommon
import org.apache.commons.lang3.StringUtils

import scala.concurrent.Await
import scala.util.control.NonFatal

case class SubmitOrder(system: ActorSystem) extends HttpCommon {


  val route = {
    path("order") {
      post {
        extractRequest { request =>


          entity(as[String]) { jsStr =>
            //         try {

            completeJson(StatusCodes.OK, "{]")


            //              val or: OrderPB = AbacusJsonParser.fromJson[OrderPB](parse(jsStr).camelizeKeys)
            //
            //
            //              val status = Await.result(
            //                orderManager ? or.update(_.metadata := AbacusMetadata(correlationId, jsStr)),
            //                timeout.duration).asInstanceOf[SubmissionStatus]
            //
            //              log.debug(s"Internal order dump (protobuf): \n${JsonUtils.quoteJSONString(or.toString)}\n")
            //
            //              val resp = SubmissionResponse(
            //                status.status,
            //                status.returnCode,
            //                status.message,
            //                status.orderExecutionId)
            //
            //              completeJson(write(resp))
            //            }
            //            catch {
            //              case ex: IllegalArgumentException => {
            //                log.error("Submission failed !", ex)
            //                val resp = SubmissionResponse(
            //                  OperationStatus.FAIL,
            //                  -2,
            //                  "FATAL ERROR: exception not currently handled. Submit issue to the development team.")
            //
            //                completeJson(write(resp))
            //              }
            //              case NonFatal(e) => {
            //
            //                log.error("Submission failed !", e)
            //
            //                val resp = SubmissionResponse(
            //                  OperationStatus.FAIL,
            //                  -1,
            //                  JsonUtils.quoteJSONString(e.getMessage))
            //
            //                log.debug(s"Raw order dump: \n${StringUtils.abbreviate(JsonUtils.quoteJSONString(jsStr), 25)}\n")
            //                completeJson(write(resp))
            //
            //              }
            //            }
            //          }
            //     }
            //   }
            // }
          }
        }

      }
    }

  }
}
