package mydatamodels.rps.domain

import java.time.LocalDate

import mydatamodels.core.domain.entities.{HumanPlayer, Match}
import mydatamodels.core.interfaces.MatchID
import mydatamodels.rps.infrastructure.InMemoryGameRecorder
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers


class ClassicGameTest extends AnyFlatSpec with Matchers {

  behavior of "ClassicGameTest"


  val _match = new Match(expectHumanPlayersCount = 1).
    withPlayerOne(new HumanPlayer(
      pseudo = "DefaultUser",
      birthDate = LocalDate.parse("1977-05-30"))) //instance.read(instance.defaultMatchID)
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
    cancel()
  }

  it should "formatMatchResult" in {
    cancel()
  }

}
