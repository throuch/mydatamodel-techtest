package mydatamodels.rps.domain

import mydatamodels.core.interfaces.MatchID
import mydatamodels.gameserver.application.injection.GameApplicationMixing
import mydatamodels.gameserver.interfaces.swagger.model.{GameAction, GameActionResponse}
import mydatamodels.rps.infrastructure.InMemoryGameRecorder
import mydatamodels.rps.interfaces.RPSElement
import org.scalatest.{FlatSpec, Matchers, WordSpec}


class ClassicGameTest extends FlatSpec with Matchers {

  behavior of "ClassicGameTest"

  implicit val instance = new GameApplicationMixing {
  }

  val _match = instance.read(instance.defaultMatchID)
  val game = new ClassicGame(_match, InMemoryGameRecorder, new AIStrategy {
    override def getHand(matchId: MatchID): ClassicElement = {
      Scissors
    }
  })

  it should "onHumanAction rock" in {

    val response = game.playRound(Rock)

    (response match {
      case (Rock, Scissors, game.GameResult.win, true) => true
      case _ => false
    }) shouldBe true

  }

  it should "onHumanAction scissors" in {

    val response = game.playRound(Scissors)

    (response match {
      case (Scissors, Scissors, game.GameResult.draw, false) => true
      case _ => false
    }) shouldBe true

  }

  it should "onHumanAction paper" in {

    val response = game.playRound(Paper)

    (response match {
      case (Paper, Scissors, game.GameResult.lose, false) => true
      case _ => false
    }) shouldBe true

  }


  it should "computeGame" in {

  }

  it should "formatMatchResult" in {

  }

}
