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
import scalafx.geometry.Insets


import de.htwg.se.blindmaze.model.grid.IGrid
import de.htwg.se.blindmaze.model.tiles.{Tile, TileContent}
import scalafx.scene.layout.{GridPane, Pane, StackPane, VBox}

object GUIrenderer {

  private val tileSize = 80 // Adjusted size of each tile in pixels

  def render(grid: IGrid, current: Int, parentWidth: Double, parentHeight: Double): GridPane = {
    // Calculate the size of each tile based on the parent dimensions with padding
    val padding = 30
    val tileSize = Math.min((parentWidth - padding * 2) / grid.size, (parentHeight - padding * 2) / grid.size)

    // Create a GridPane to hold the tiles
    val gridPane = new GridPane()
    gridPane.setPadding(new javafx.geometry.Insets(padding))

    for (y <- 0 until grid.size; x <- 0 until grid.size) {
      val tile = grid.get(Position(x, y))
      val tilePane = renderTile(tile, current, tileSize)
      gridPane.add(tilePane, x, y)
    }

    gridPane
  }

  private def renderTile(tile: Tile, current: Int, tileSize: Double): Pane = {
    val pane = new StackPane()

    // Background rectangle for the tile with rounded corners and dark background
    val rect = new Rectangle {
      width = tileSize
      height = tileSize
      fill = tile.content match {
        case TileContent.Empty => Color.web("#333333")
        case TileContent.Wall(visible) => if (visible) Color.web("#555555") else Color.web("#333333")
        case TileContent.Player(id) => if (id == current) Color.web("#FF5733") else Color.web("#2E86C1")
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

  def renderWinner(player: Int): VBox = {
    new VBox {
      padding = Insets(0, 0, 0, 20)
      stylesheets.add(getClass.getResource("/css/menu.css").toExternalForm)
      alignment = Pos.CENTER_LEFT
      children = Seq(
        new Text(s"Player $player wins!") {
          font = Font(40)
          spacing = 20
        },
        new Button("Leave") {
          onAction = _ => System.exit(0) // Exit the application
        }
      )
    }
  }
}
