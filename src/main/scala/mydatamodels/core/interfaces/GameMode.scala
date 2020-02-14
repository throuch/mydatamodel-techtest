package mydatamodels.core.interfaces


object GameMode extends Enumeration {
  type GameMode = Value
  val HumanVsComputer, ComputerVsComputer, Quit = Value
}
