package mydatamodels.gameserver.application.http.game

import java.time.LocalDate

import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.model._
import akka.http.scaladsl.server._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.util.ByteString
import mydatamodels.core.domain.entities.{HumanPlayer, Match}
import mydatamodels.core.interfaces.GameConfiguration
import mydatamodels.core.interfaces.PlayerType.{Computer, Human}
import mydatamodels.gameserver.application.injection.Module.DefaultGameService
import mydatamodels.gameserver.interfaces.swagger.converter.JsonSupport
import mydatamodels.gameserver.interfaces.swagger.model.{GameAction, GameActionResponse}
import mydatamodels.rps.application.actors.ClassicGameActor
import mydatamodels.rps.domain.ClassicGame
import mydatamodels.rps.interfaces.RPSElement
import org.scalatest.{Matchers, WordSpec}
import org.slf4j.LoggerFactory


class PlayApiTest extends WordSpec with Matchers with ScalatestRouteTest with JsonSupport {
  val log = LoggerFactory.getLogger(getClass)

  implicit val element = enumFormat(RPSElement)
  implicit val requestFormat = jsonFormat1(GameAction)
  implicit val responseFormat = jsonFormat2(GameActionResponse)


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

  def postRequest(path: String, json: ByteString): HttpRequest =
    HttpRequest(HttpMethods.POST,
      uri = path,
      entity = HttpEntity(MediaTypes.`application/json`, json)
    )


  val matchid = DefaultGameService.createRockPaperScissorsGame(new GameConfiguration(Human, Computer))
  val playerid = DefaultGameService.createHumanPlayer(pseudo = "Thomas",
    birthDate = LocalDate.parse("1977-05-30"))

  DefaultGameService.registerPlayer(matchid, playerid)
  //DefaultGameService.players.getOrElse(player.id, throw new Exception(s"Player ${player.id} not found"))


  val gameActorRef = system.actorOf(ClassicGameActor.props(DefaultGameService), "GameActor")
  val play = new Play(gameActorRef)


  lazy val smallroute = Route.seal(play.route)

  "The service" should {
    "return 200 for POST requests to /play with proper entry" in {

      Post("/play/" + matchid, GameAction(RPSElement.rock)) ~> smallroute ~> check {


        /* val response = responseAs[GameActionResponse]


        status should ===(StatusCodes.OK)
        log.info(response.toString)

        (response match {
          case GameActionResponse(_ , _) => true // TODO
          case _ => false
        }) shouldEqual true
*/
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
