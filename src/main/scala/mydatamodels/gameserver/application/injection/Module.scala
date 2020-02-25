package mydatamodels.gameserver.application.injection


import mydatamodels.core.application.service.MatchService
import mydatamodels.core.infrastructure.{inMemoryMatchRepository, inMemoryPlayerRepository}
import mydatamodels.gameserver.application.service.{BasicFeatures, GameLauncher}
import mydatamodels.gameserver.interfaces.GameService
import mydatamodels.rps.domain.{AIStrategy, AdvancedGameStrategy, RandomGameStrategy}
import mydatamodels.rps.infrastructure.InMemoryGameRecorder


trait MatchServiceImpl extends MatchService {
  val playerRepo = inMemoryPlayerRepository
  val matchRepo = inMemoryMatchRepository
}

trait GameApplicationMixing extends
  GameService with
  MatchServiceImpl with
  GameLauncher with
  ComputerAI with
  BasicFeatures


trait ComputerAI {
  val defaultGameStrategy: AIStrategy = new AdvancedGameStrategy {
    val repo = InMemoryGameRecorder
  }
  val randomGameStrategy = new RandomGameStrategy {}
}



