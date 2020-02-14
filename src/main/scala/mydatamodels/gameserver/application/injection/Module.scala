package mydatamodels.gameserver.application.injection


import mydatamodels.core.application.service.CommonGameService
import mydatamodels.gameserver.application.service.GameLauncher
import mydatamodels.gameserver.interfaces.GameService

object Module {

  object DefaultGameService extends GameService with CommonGameService with GameLauncher

}
