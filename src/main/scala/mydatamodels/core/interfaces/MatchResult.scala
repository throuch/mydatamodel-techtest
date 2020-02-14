package mydatamodels.core.interfaces

case class MatchResult(winner: PlayerID, loser: PlayerID, rounds: List[RoundResult]) {

}
