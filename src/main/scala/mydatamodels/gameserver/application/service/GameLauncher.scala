package mydatamodels.gameserver.application.service

import mydatamodels.core.application.service.CommonGameService
import mydatamodels.core.interfaces.{GameConfiguration, MatchID}
import mydatamodels.gameserver.application.http.OrderManagerHttpServer
import mydatamodels.rps.domain.ClassicGame

import scala.collection.mutable


trait GameLauncher {
  self: CommonGameService =>
  val games: mutable.Map[MatchID, ClassicGame] = mutable.HashMap[MatchID, ClassicGame]()


  def createRockPaperScissorsGame(config: GameConfiguration): MatchID = {
    val _match = createMatch(config)
    val game = new ClassicGame(_match)
    games.put(_match.id, game)
    _match.id
  }


  def start(matchID: MatchID): Unit = {
    val game =  games.get(matchID).get

    if (!game._match.isMatchReadyToStart)
        throw new IllegalStateException

    new OrderManagerHttpServer

  }

}
