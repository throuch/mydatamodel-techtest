package mydatamodels.core.domain.entities

import java.util.UUID

import mydatamodels.core.application.service.MatchService
import mydatamodels.core.domain.{ComputerPlayer1, ComputerPlayer2, Player}
import mydatamodels.core.interfaces.MatchID

/**
 *
 * Constraint: Computer is always considered as "home" in a match Human Vs CPU
 *
 * @param id
 * @param homeScore
 * @param visitorScore
 *
 */
class Match(val id: MatchID = UUID.randomUUID(),
            var homeScore: Int = 0,
            var visitorScore: Int = 0,
            val expectHumanPlayersCount: Short) {

  private var homePlayer: Player = ComputerPlayer1
  private var visitorPlayer: Player = ComputerPlayer2
  private val humanPlayerSlots: Array[Boolean] = Array.fill[Boolean](expectHumanPlayersCount)(false)


  def getVisitorPlayer: Player = visitorPlayer

  def getHomePlayer: Player = homePlayer

  def withPlayerOne(player: HumanPlayer): this.type = {
    if (expectHumanPlayersCount != 0) {
      visitorPlayer = player
      humanPlayerSlots(0) = true
    }
    else
      new Exception("This match expects no human players")
    this
  }


  def withPlayerTwo(player: HumanPlayer): this.type = {
    if (expectHumanPlayersCount == 2) {
      homePlayer = player
      humanPlayerSlots(1) = true
    }
    else
      new Exception("This match expects 2 human players")
    this
  }


  def with2HumanPlayers(player1: HumanPlayer, player2: HumanPlayer): this.type = {
    if (expectHumanPlayersCount == 2) {
      homePlayer = player1
      visitorPlayer = player2
      humanPlayerSlots(0) = true
      humanPlayerSlots(1) = true
    }
    else
      new Exception("This match expect 2 human players")
    this
  }

  def isMatchReadyToStart: Boolean = {
    humanPlayerSlots.forall(_ == true)
  }

  def incrementVisitorScore() {
    visitorScore += 1
  }

  def incrementHomeScore(): Unit = {
    homeScore += 1
  }
}


object Match {

  def apply(human: HumanPlayer): Match = {
    val m = new Match(expectHumanPlayersCount = 1)
    m.withPlayerOne(human)
    m
  }

  def apply(): Match = {
    new Match(expectHumanPlayersCount = 0)
  }

}