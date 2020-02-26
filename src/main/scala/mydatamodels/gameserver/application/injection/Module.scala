package mydatamodels.gameserver.application.injection


import mydatamodels.core.application.service.MatchService
import mydatamodels.core.infrastructure.{inMemoryMatchRepository, inMemoryPlayerRepository}
import mydatamodels.gameserver.application.service.GameLauncher
import mydatamodels.gameserver.interfaces.GameService
import mydatamodels.rps.domain.{AIStrategy, AdvancedGameStrategy, RandomGameStrategy}
import mydatamodels.rps.infrastructure.inMemoryGameRecorder


trait MatchServiceImpl extends MatchService {
  val playerRepo = inMemoryPlayerRepository
  val matchRepo = inMemoryMatchRepository
}

trait GameApplicationMixing extends
  GameService with
  MatchServiceImpl with
  GameLauncher with
  ComputerAI


trait ComputerAI {
  val defaultGameStrategy: AIStrategy = new AdvancedGameStrategy {
    val repo = inMemoryGameRecorder
  }
  val randomGameStrategy = new RandomGameStrategy {}
}



