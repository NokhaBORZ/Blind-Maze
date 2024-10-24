// Player.scala
import CellState._

case class Player(var x: Int, var y: Int, symbol: String) {

  // Bewegt den Spieler in die angegebene Richtung
  def move(direction: String, grid: Grid): Unit = {
    direction match {
      case "w" if y > 0 && grid.cells(y - 1)(x).isPassable() =>
        if (grid.cells(y - 1)(x).state == WeakPath) grid.cells(y - 1)(x).useWeakPath()
        y -= 1
      case "a" if x > 0 && grid.cells(y)(x - 1).isPassable() =>
        if (grid.cells(y)(x - 1).state == WeakPath) grid.cells(y)(x - 1).useWeakPath()
        x -= 1
      case "s" if y < grid.height - 1 && grid.cells(y + 1)(x).isPassable() =>
        if (grid.cells(y + 1)(x).state == WeakPath) grid.cells(y + 1)(x).useWeakPath()
        y += 1
      case "d" if x < grid.width - 1 && grid.cells(y)(x + 1).isPassable() =>
        if (grid.cells(y)(x + 1).state == WeakPath) grid.cells(y)(x + 1).useWeakPath()
        x += 1
      case _ =>
        println(s"$symbol kann sich nicht in diese Richtung bewegen!")
    }
  }
}
