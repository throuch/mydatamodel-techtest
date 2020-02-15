package mydatamodels.gameserver.application.injection


import mydatamodels.core.application.service.CommonGameService
import mydatamodels.core.domain.MatchRecorder
import mydatamodels.core.infrastructure.InMemoryMatchRecorder
import mydatamodels.gameserver.application.service.GameLauncher
import mydatamodels.gameserver.interfaces.GameService
import mydatamodels.rps.domain.{AdvancedGameStrategy, PlayActionRecorder}

object Module {

  val DefaultMatchRecorder: MatchRecorder = InMemoryMatchRecorder

  object DefaultGameService extends GameService with CommonGameService with GameLauncher

  object DefaultComputerAI extends PlayActionRecorder with AdvancedGameStrategy


}
