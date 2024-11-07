package de.htwg.se.blindmaze.model

import de.htwg.se.blindmaze.math.{Direction, Position}

class Player(var position: Position) {

  private var inventory: List[Item] = List()

  def move(direction: Direction, grid: Grid): Boolean = {
    val newPosition = position.move(direction)
    if (grid.isValidMove(newPosition)) {
      grid.getTile(position).foreach(_.setOccupied(false))
      position = newPosition
      grid.getTile(position).foreach(_.setOccupied(true))
      true
    } else {
      false
    }
  }

  def collectItem(grid: Grid): Option[Item] = {
    grid.getTile(position).flatMap { tile =>
      val collectedItem = tile.pickUpItem()
      collectedItem.foreach(item => inventory = item :: inventory)
      collectedItem
    }
  }

  def checkVictory(grid: Grid): Boolean = {
    grid.getTile(position).exists(_.isVictoryTile)
  }

  def showInventory(): List[String] = {
    inventory.map(_.name)
  }
}
