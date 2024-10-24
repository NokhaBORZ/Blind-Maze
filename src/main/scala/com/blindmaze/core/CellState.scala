// CellState.scala
object CellState extends Enumeration {
  type CellState = Value
  val Empty, Path, Visited, Player, Fake, WeakPath = Value
}
