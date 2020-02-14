package mydatamodels.core.domain.entities

import java.time.LocalDate
import java.util.UUID

import mydatamodels.core.domain.Player
import mydatamodels.core.interfaces.PlayerID


class HumanPlayer(override val id: PlayerID= UUID.randomUUID(),
                  pseudo: String,
                  birthDate: LocalDate) extends Player  {
  override val name = s"Player $pseudo"
}
