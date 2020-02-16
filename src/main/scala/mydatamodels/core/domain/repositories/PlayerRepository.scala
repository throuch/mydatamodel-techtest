package mydatamodels.core.domain.repositories

import mydatamodels.core.domain.entities.HumanPlayer
import mydatamodels.core.interfaces.PlayerID

import scala.collection.mutable

trait PlayerRepository {
  val players: mutable.Map[PlayerID, HumanPlayer] = mutable.HashMap[PlayerID, HumanPlayer]()

}
