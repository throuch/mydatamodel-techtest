package mydatamodels.gameserver.application.injection


import mydatamodels.core.application.service.{CommonGameService, MatchService}
import mydatamodels.core.domain.repositories.PlayerRepository
import mydatamodels.core.infrastructure.{InMemoryMatchRepository, InMemoryPlayerRepository}
import mydatamodels.gameserver.application.service.GameLauncher
import mydatamodels.rps.domain.AdvancedGameStrategy
import mydatamodels.rps.infrastructure.InMemoryGameRecorder

object Module {


  object DefaultGameService extends
    MatchService with
    InMemoryMatchRepository with
    CommonGameService with
    GameLauncher with
    InMemoryPlayerRepository


  object DefaultComputerAI extends {
    val repo = InMemoryGameRecorder
  } with AdvancedGameStrategy


}
