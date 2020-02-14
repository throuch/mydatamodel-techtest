package mydatamodels.core.domain

import mydatamodels.core.interfaces.PlayerID


trait Player {
  val id: PlayerID
  val name: String

  def canEqual(other: Any): Boolean = other.isInstanceOf[Player]

  override def equals(other: Any): Boolean = other match {
    case that: Player =>
      (that canEqual this) &&
        id == that.id
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(id)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

}
