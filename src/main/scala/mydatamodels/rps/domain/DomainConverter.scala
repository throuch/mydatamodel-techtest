package mydatamodels.rps.domain

import mydatamodels.rps.interfaces.RPSElement

object DomainConverter {

  def toDomain(e: RPSElement.RPSElement): ClassicElement = {
    e match {
      case RPSElement.`rock` => Rock
      case RPSElement.`scissors` => Scissors
      case RPSElement.`paper` => Paper
      case _ => throw new IllegalArgumentException
    }

  }
}
