package mydatamodels.gameserver.application.injection


import mydatamodels.core.application.service.MatchService
import mydatamodels.core.infrastructure.{InMemoryMatchRepository, InMemoryPlayerRepository}
import mydatamodels.gameserver.application.service.{BasicFeatures, GameLauncher}
import mydatamodels.gameserver.interfaces.GameService
import mydatamodels.rps.domain.{AIStrategy, AdvancedGameStrategy, RandomGameStrategy}
import mydatamodels.rps.infrastructure.InMemoryGameRecorder


trait GameApplicationMixing extends
  GameService with
  MatchService with
  InMemoryMatchRepository with

  GameLauncher with
  InMemoryPlayerRepository with
  ComputerAI with
  BasicFeatures


trait ComputerAI {
  val defaultGameStrategy: AIStrategy = new AdvancedGameStrategy {
    val repo = InMemoryGameRecorder
  }
  val randomGameStrategy = new RandomGameStrategy {}
}



