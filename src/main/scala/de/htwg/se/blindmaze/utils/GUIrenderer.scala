package de.htwg.se.blindmaze.utils

import scalafx.application.Platform
import scalafx.scene.Scene
import scalafx.scene.layout.{GridPane, Pane}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.{Font, Text}
import scalafx.stage.Stage

import de.htwg.se.blindmaze.model.{Grid, Position}
import de.htwg.se.blindmaze.model.tiles.{Tile, TileContent}
import scalafx.scene.layout.StackPane

object GUIrenderer {

  private val tileSize = 50 // Size of each tile in pixels

  def render(grid: Grid): GridPane = {
    // Create a GridPane to hold the tiles
    val gridPane = new GridPane()

    for (y <- 0 until grid.size; x <- 0 until grid.size) {
      val tile = grid.get(Position(x, y))
      val tilePane = renderTile(tile)
      gridPane.add(tilePane, x, y)
    }

    gridPane
  }

  private def renderTile(tile: Tile): Pane = {
    val pane = new Pane()

    // Background rectangle for the tile
    val rect = new Rectangle {
      width = tileSize
      height = tileSize
      fill = tileColor(tile)
      stroke = Color.Black
      strokeWidth = 1
    }

    // Text to represent the content of the tile
    val contentText = new Text {
      text = tileContentText(tile.content)
      font = Font.font(20)
      fill = Color.Black
      x = tileSize / 4
      y = tileSize / 1.5
    }

    pane.children.addAll(rect, contentText)
    pane
  }

  private def tileColor(tile: Tile): Color = {
    tile.content match {
      case TileContent.Empty      => Color.LightGray
      case TileContent.Player(_)  => Color.Green
      case TileContent.Wall       => Color.DarkGray
      case TileContent.Victory    => Color.Gold
      case TileContent.OutOfBounds => Color.Red
      case TileContent.Trap       => Color.Purple
      case TileContent.ChestTile(_) => Color.Blue
    }
  }

  private def tileContentText(content: TileContent): String = {
    content match {
      case TileContent.Empty      => ""
      case TileContent.Player(id) => s"P$id"
      case TileContent.Wall       => "#"
      case TileContent.Victory    => "V"
      case TileContent.OutOfBounds => "X"
      case TileContent.Trap       => "T"
      case TileContent.ChestTile(_) => "C"
    }
  }
}