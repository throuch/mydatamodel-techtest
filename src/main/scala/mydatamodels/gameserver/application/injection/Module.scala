package mydatamodels.gameserver.application.injection


import mydatamodels.core.application.service.{CommonGameService, MatchService}
import mydatamodels.core.domain.repositories.{MatchRepository, PlayerRepository}
import mydatamodels.core.infrastructure.InMemoryMatchRepository
import mydatamodels.gameserver.application.service.GameLauncher
import mydatamodels.gameserver.interfaces.GameService
import mydatamodels.rps.domain.AdvancedGameStrategy
import mydatamodels.rps.infrastructure.InMemoryGameRecorder

object Module {


  object DefaultGameService extends

    MatchService with PlayerRepository with
    InMemoryMatchRepository with
    CommonGameService with
    GameLauncher


  object DefaultComputerAI extends {
    val repo = InMemoryGameRecorder
  } with AdvancedGameStrategy


}
