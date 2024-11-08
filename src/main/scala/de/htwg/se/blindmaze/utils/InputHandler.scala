package de.htwg.se.blindmaze.utils

import de.htwg.se.blindmaze.core.Grid
import de.htwg.se.blindmaze.core.TileContent.Player
import de.htwg.se.blindmaze.math.{Direction, Position}

enum Input {
  case Move(direction: Direction)
  case Quit
}

object InputHandler {
  def parseInput(input: String): Option[Input] = {
    input.toLowerCase() match {
      case "w" => Some(Input.Move(Direction.Up))
      case "s" => Some(Input.Move(Direction.Down))
      case "a" => Some(Input.Move(Direction.Left))
      case "d" => Some(Input.Move(Direction.Right))
      case "q" => Some(Input.Quit)
      case _   => None
    }
  }
}
