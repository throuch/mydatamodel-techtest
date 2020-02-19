package mydatamodels.core.infrastructure

import mydatamodels.core.domain.entities.Match
import mydatamodels.core.domain.repositories.MatchObjectValue

object InfraConverter {


  def toInfra(m: Match): MatchObjectValue = {
    MatchObjectValue(m.id, m.homeScore, m.visitorScore, m.getVisitorPlayer.id)
  }
}
