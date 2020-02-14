package mydatamodels.rps.domain

import mydatamodels.core.domain.entities.{HumanPlayer, Match}
import mydatamodels.core.domain.{ComputerPlayer1, ComputerPlayer2, Game, Player}


/**
 *
 *
 *
 **/
class ClassicGame(_m: Match) extends Game[ClassicElement](_m, new ClassicGameEngine) {


  /* def start = {
    assert(_m.isMatchReadyToStart)
  }
*/
}
