package mydatamodels.rps.domain

import mydatamodels.rps.interfaces.RPSElement

object DomainConverter {

  def toDomain(e: RPSElement.RPSElement): ClassicElement = {
    e match {
      case RPSElement.Rock => Rock
      case RPSElement.Scissors => Scissors
      case RPSElement.Paper => Paper
      case _ => throw new IllegalArgumentException
    }

  }
}
