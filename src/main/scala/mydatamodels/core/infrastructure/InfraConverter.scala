package mydatamodels.core.infrastructure

import mydatamodels.core.domain.entities.Match
import mydatamodels.core.domain.repositories.MatchObjectValue

object InfraConverter {
  def toDomain(m: MatchObjectValue): Match = {
    //???
    val domainMatch = new Match(m.matchId, m.computerScore, m.humanScore, 1, 1)
    domainMatch
  }

  def toInfra(m: Match): MatchObjectValue = {
    MatchObjectValue(m.id, m.homeScore, m.visitorScore, m.getVisitorPlayer.id)
  }
}
