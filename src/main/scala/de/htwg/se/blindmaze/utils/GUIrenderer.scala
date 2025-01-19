package de.htwg.se.blindmaze.utils

import scalafx.application.Platform
import scalafx.scene.Scene
import scalafx.scene.layout.{GridPane, Pane, StackPane}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.{Font, Text}
import scalafx.stage.Stage
import scalafx.geometry.Pos
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.VBox


import de.htwg.se.blindmaze.model.grid.IGrid
import de.htwg.se.blindmaze.model.tiles.{Tile, TileContent}
import scalafx.scene.layout.{GridPane, Pane, StackPane, VBox}

object GUIrenderer {

  private val tileSize = 80 // Adjusted size of each tile in pixels

  def render(grid: IGrid): GridPane = {
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
    val pane = new StackPane()

    // Background rectangle for the tile with rounded corners and dark background
    val rect = new Rectangle {
      width = tileSize
      height = tileSize
      fill = tile.content match {
        case TileContent.Empty => Color.web("#333333")
        case TileContent.Wall => Color.web("#555555")
        case TileContent.Player(_) => Color.web("#0077FF")
        case TileContent.Victory => Color.web("#00FF00")
        case TileContent.OutOfBounds => Color.web("#000000")
        case TileContent.Trap => Color.web("#FF0000")
        case TileContent.ChestTile(_) => Color.web("#FFD700")
      }
      arcWidth = 15 // Rounded corners
      arcHeight = 15 // Rounded corners
      stroke = Color.web("#555555") // Slight border color for clarity
      strokeWidth = 2
    }

    // Example content (e.g., text or icons can be added here)
    val content = new Text {
      fill = Color.White
      font = Font("Arial", 16)
    }

    pane.children.addAll(rect, content)
    pane
  }

  def renderWinner(player: Int): StackPane = {
    new StackPane  {
      val winnerText = new Text(s"Player $player wins!") {
        font = Font(40)
      }

      val button = new Button("Close") {
        onAction = _ => System.exit(0) // Exit the application
      }

      val vbox = new VBox(20, winnerText, button) {
        alignment = Pos.Center
      }
    }
  }
}
