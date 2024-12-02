package de.htwg.se.blindmaze.utils

import de.htwg.se.blindmaze.model.{Grid, Position}
import de.htwg.se.blindmaze.model.tiles.{Tile, TileContent}

object Renderer{
  def render(grid: Grid): String = {
    val horizontal = horizontalLine(grid.size)

    val rows = for (y <- 0 until grid.size) yield {
      val row = for (x <- 0 until grid.size) yield {
        val tile = grid.get(Position(x, y))
        s"|${renderTile(tile)}"
      }
      row.mkString + "|"
    }

    (horizontal +: rows.flatMap(row => Seq(row, horizontal))).mkString("\n")
  }

  def horizontalLine(size: Int): String = {
    val line = "+---" * size + "+"
    line
  }

  def renderTile(tile: Tile): String = {
    tile.content match {
        case TileContent.Empty      => "   "
        case TileContent.Player(id) => s" ${id} "
        case TileContent.Wall       => " # "
        case TileContent.Victory => " V "
        case TileContent.OutOfBounds => " X "
        case TileContent.Trap => " T "
        case TileContent.ChestTile(chest) => " C "
    }
  }
}