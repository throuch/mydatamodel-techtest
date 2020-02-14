package mydatamodels.core.domain

import java.util.UUID
import mydatamodels.core.interfaces.PlayerID


trait Computer extends Player {
  override val id: PlayerID = UUID.randomUUID()
}

object ComputerPlayer1 extends Computer {
  override val name = "Computer \"Joshua\""
}

object ComputerPlayer2 extends Computer {
  override val name = "Computer \"Skynet\""
}
