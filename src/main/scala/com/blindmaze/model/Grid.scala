package com.blindmaze.model

import com.blindmaze.math.{Direction, Position}

// Klasse Grid repräsentiert das Spielfeld als eine 2D-Array von Tiles
class Grid(val size: Int) {
  val tiles: Array[Array[Tile]] = Array.tabulate(size, size)((x, y) => new Tile(Position(x, y)))

  // Methode, um ein Item auf ein bestimmtes Tile zu platzieren
  def placeItem(item: Item, position: Position): Unit = {
    val tile = getTile(position)
    if (tile.isDefined) {
      tile.get.placeItem(item)
    } else {
      println(s"Position ${position} is out of bounds")
    }
  }

  // Methode, um einen Spieler zu bewegen
  def movePlayer(player: Player, direction: Direction.Value): Boolean = {
    val newPosition = direction match {
      case Direction.Up    => player.position.copy(y = player.position.y - 1)
      case Direction.Down  => player.position.copy(y = player.position.y + 1)
      case Direction.Left  => player.position.copy(x = player.position.x - 1)
      case Direction.Right => player.position.copy(x = player.position.x + 1)
    }

    if (isValidPosition(newPosition)) {
      val newTile = getTile(newPosition).get
      if (!newTile.isBlocked) {
        player.move(direction)
        println(s"Player moved to: $newPosition")
        return true
      } else {
        println("Tile is blocked")
      }
    } else {
      println("Invalid move, out of bounds")
    }
    false
  }

  // Methode, um zu überprüfen, ob eine Position innerhalb des Grids gültig ist
  def isValidPosition(position: Position): Boolean = {
    position.x >= 0 && position.x < size && position.y >= 0 && position.y < size
  }

  // Methode, um ein Tile an einer bestimmten Position zu bekommen
  private def getTile(position: Position): Option[Tile] = {
    if (isValidPosition(position)) Some(tiles(position.x)(position.y)) else None
  }
}
