package mydatamodels.rps.domain

import mydatamodels.core.domain.entities.{HumanPlayer, Match}
import mydatamodels.core.domain.{ComputerPlayer1, ComputerPlayer2, Game, Player}


/**
 * Fast implementation  of classical game Human Vs Computer
 *
 * in the future, could be an Actor (reactive)
 * */
class ClassicGame (_m: Match) extends Game[ClassicElement](_m, new ClassicGameEngine) {




  def start = {
    assert(_m.isMatchReadyToStart)


    /* game runthrough:
      wait for player 1 action

      wait for player 2 action
      compare results

      display results
     */
    println("[NEW GAME]")



    println(s"plays :> ${elementTwo.toString}")
    val result = gameEngine.compare(
      DomainConverter.toDomain(elementOne),
      DomainConverter.toDomain(elementTwo))

    if (result > 0) {
      println(s"${_match.getVisitorPlayer.name} wins !")
    }

    else if (result < 0) {
      println(s"${_match.getHomePlayer.name} wins !")
    }
    else {
      println("Draw game !")
    }

    println("\n")

  }

}
