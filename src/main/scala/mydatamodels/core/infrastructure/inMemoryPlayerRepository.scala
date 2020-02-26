package mydatamodels.core.infrastructure

import mydatamodels.core.domain.entities.HumanPlayer
import mydatamodels.core.domain.repositories.PlayerRepository
import mydatamodels.core.interfaces.PlayerID

import scala.collection.mutable

object inMemoryPlayerRepository extends PlayerRepository {
  final val players: mutable.Map[PlayerID, HumanPlayer] = mutable.HashMap[PlayerID, HumanPlayer]()
}
