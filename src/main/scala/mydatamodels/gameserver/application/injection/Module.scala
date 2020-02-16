package mydatamodels.gameserver.application.injection


import mydatamodels.core.application.service.MatchService
import mydatamodels.core.infrastructure.{InMemoryMatchRepository, InMemoryPlayerRepository}
import mydatamodels.gameserver.application.service.{BasicFeatures, GameLauncher}
import mydatamodels.gameserver.interfaces.GameService
import mydatamodels.rps.domain.AdvancedGameStrategy
import mydatamodels.rps.infrastructure.InMemoryGameRecorder

object Module {


  object DefaultGameService extends
    GameService with
    MatchService with
    InMemoryMatchRepository with

    GameLauncher with
    InMemoryPlayerRepository with
    BasicFeatures


  object DefaultComputerAI extends {
    val repo = InMemoryGameRecorder
  } with AdvancedGameStrategy


}
