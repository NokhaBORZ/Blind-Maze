package de.htwg.se.blindmaze.math

import de.htwg.se.blindmaze.math.Direction

case class Position(x: Int, y: Int) {

  def move(direction: Direction): Position = {
    direction match {
      case Direction.Up    => Position(x, y - 1)
      case Direction.Down  => Position(x, y + 1)
      case Direction.Left  => Position(x - 1, y)
      case Direction.Right => Position(x + 1, y)
    }
  }
}
