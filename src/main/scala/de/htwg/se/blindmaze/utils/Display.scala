package de.htwg.se.blindmaze.utils

import de.htwg.se.blindmaze.model.{Grid, Player, Tile}
import de.htwg.se.blindmaze.math.Position

class Display(grid: Grid, players: List[Player]) {

  // Display the grid with player, items, and blocked paths
  def showGrid(): Unit = {
    for (y <- 0 until grid.size) {
      for (x <- 0 until grid.size) {
        val position = Position(x, y)
        val tile = grid.getTile(position)

        print(getSymbol(tile, position))
      }
      println() // New line after each row
    }
  }

  // Determine the appropriate symbol for each tile
  private def getSymbol(tileOpt: Option[Tile], position: Position): String = {
    tileOpt match {
      case Some(tile) if players.exists(_.position == position) => "P"  // Player
      case Some(tile) if tile.item.isDefined                   => "I"  // Item
      case Some(tile) if tile.isBlocked                        => "+"  // Blocked path
      case Some(_)                                             => "."  // Open path
      case None                                                => " "  // Out of bounds (shouldn't occur)
    }
  }
}
