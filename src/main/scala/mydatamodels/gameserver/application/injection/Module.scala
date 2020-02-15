package mydatamodels.gameserver.application.injection


import mydatamodels.core.application.service.CommonGameService
import mydatamodels.gameserver.application.service.GameLauncher
import mydatamodels.gameserver.interfaces.GameService
import mydatamodels.rps.domain.AdvancedGameStrategy
import mydatamodels.rps.infrastructure.InMemoryGameRecorder

object Module {


  object DefaultGameService extends GameService with CommonGameService with GameLauncher


  object DefaultComputerAI extends {
    val repo = InMemoryGameRecorder
  } with AdvancedGameStrategy


}
