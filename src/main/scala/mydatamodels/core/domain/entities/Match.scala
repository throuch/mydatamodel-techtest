package mydatamodels.core.domain.entities

import java.util.UUID

import mydatamodels.core.domain.{ComputerPlayer1, ComputerPlayer2, Player}
import mydatamodels.core.interfaces.MatchID

/**
 *
 * Constraint: Computer is always considered as "home" in a match Human Vs CPU
 *
 * @param id
 * @param homeScore
 * @param visitorScore
 * @param roundCount
 */
class Match(val id: MatchID = UUID.randomUUID(),
            val homeScore: Int = 0,
            val visitorScore: Int = 0,
            roundCount: Short = 1,
            val expectHumanPlayersCount: Short) {

  private var homePlayer: Player = ComputerPlayer1
  private var visitorPlayer: Player = ComputerPlayer2
  private val humanPlayerSlots: Array[Boolean] = Array.fill[Boolean](expectHumanPlayersCount)(false)


  def getVisitorPlayer: Player = visitorPlayer

  def getHomePlayer: Player = homePlayer

  def registerPlayerOne(player: HumanPlayer): Unit = {
    if (expectHumanPlayersCount != 0) {
      visitorPlayer = player
      humanPlayerSlots(0) = true
    }
    else
      new Exception("This match expects no human players")
  }

  def registerPlayerTwo(player: HumanPlayer): Unit = {
    if (expectHumanPlayersCount == 2) {
      homePlayer = player
      humanPlayerSlots(1) = true
    }
    else
      new Exception("This match expects 2 human players")
  }


  def register2HumanPlayers(player1: HumanPlayer, player2: HumanPlayer): Unit = {
    if (expectHumanPlayersCount == 2) {
      homePlayer = player1
      visitorPlayer = player2
      humanPlayerSlots(0) = true
      humanPlayerSlots(1) = true
    }
    else
      new Exception("This match expect 2 human players")
  }

  def isMatchReadyToStart: Boolean = {
    humanPlayerSlots.forall(_ == true)
  }
}


object Match {

  def apply(human: HumanPlayer): Match = {
    val m = new Match(expectHumanPlayersCount = 1)
    m.registerPlayerOne(human)
    m
  }

  def apply(): Match = {
    new Match(expectHumanPlayersCount = 0)
  }

}