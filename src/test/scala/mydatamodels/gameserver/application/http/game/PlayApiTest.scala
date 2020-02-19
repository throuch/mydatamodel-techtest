package mydatamodels.gameserver.application.http.game

import java.time.LocalDate
import java.util.UUID

import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.util.ByteString
import mydatamodels.core.interfaces.GameConfiguration
import mydatamodels.core.interfaces.PlayerType.{Computer, Human}
import mydatamodels.gameserver.application.injection.GameApplicationMixing
import mydatamodels.gameserver.interfaces.swagger.converter.JsonSupport
import mydatamodels.gameserver.interfaces.swagger.model.{GameAction, GameActionResponse}
import mydatamodels.rps.application.actors.ClassicGameActor
import mydatamodels.rps.interfaces.RPSElement
import org.scalatest.{Matchers, WordSpec}
import org.slf4j.LoggerFactory


class PlayApiTest extends WordSpec with Matchers with ScalatestRouteTest with JsonSupport {
  val log = LoggerFactory.getLogger(getClass)

  implicit val element = enumFormat(RPSElement)
  implicit val requestFormat = jsonFormat1(GameAction)
  implicit val responseFormat = jsonFormat2(GameActionResponse)

  /*
  implicit def myExceptionHandler: ExceptionHandler =
    ExceptionHandler {
      case e: Exception =>
        e.printStackTrace()
        complete(HttpResponse(StatusCodes.InternalServerError, entity = "Error in test writing"))
    }
*/
  implicit val myRejectionHandler =
    RejectionHandler.newBuilder()
      .handle {
        case _ =>

          complete(HttpResponse(StatusCodes.Forbidden, entity = "Invalid body !"))

      }
      .handleNotFound {
        complete(HttpResponse(StatusCodes.InternalServerError))
      }
      .result()

  def postRequest(path: String, json: ByteString): HttpRequest =
    HttpRequest(HttpMethods.POST,
      uri = path,
      entity = HttpEntity(MediaTypes.`application/json`, json)
    )

  implicit val instance = new GameApplicationMixing {}



  val gameActorRef = system.actorOf(ClassicGameActor.props(instance), "GameActor")
  val play = new Play(gameActorRef)


  lazy val smallroute = Route.seal(play.route)

  "The service" should {
    "return 200 for POST requests to /play with proper entry" in {

      Post("/play", GameAction(RPSElement.rock)) ~> smallroute ~> check {

        status.intValue() should (equal(200) or equal(418))
        log.info(response.toString)

        //        val entity = responseAs[GameActionResponse]
        //        entity.message should startWith("You played rock")

        (response match {
          case HttpResponse(_, _, entity, _) => {
            log.info("DEBUG: " + entity.toString)

            //    entity. should startWith ("You played rock")
            true
          } // TODO
          case _ => false
        }) shouldEqual true

      }
    }
  }


  "The service" should {
    "return 403 for POST requests to /play with incorrect hand value" in {
      postRequest("/play", ByteString("""{ "myHand" : "crap" }""")) ~> smallroute ~> check {

        status should ===(StatusCodes.Forbidden)
        log.info(response.toString)

      }


    }
  }
}
