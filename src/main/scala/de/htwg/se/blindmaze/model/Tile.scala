package de.htwg.se.blindmaze.model

import de.htwg.se.blindmaze.math.Position

class Tile(
    val position: Position,
    var isBlocked: Boolean = false,
    var item: Option[Item] = None,
    var isVictoryTile: Boolean = false
) {
  
  private var occupiedByPlayer: Boolean = false

  def isOccupied: Boolean = occupiedByPlayer

  def setOccupied(status: Boolean): Unit = {
    occupiedByPlayer = status
  }

  def placeItem(newItem: Item): Unit = {
    if (item.isEmpty) {
      item = Some(newItem)
      println(s"Item ${newItem.name} placed at position: $position")
    } else {
      println("Tile already contains an item")
    }
  }

  def pickUpItem(): Option[Item] = {
    val pickedItem = item
    item = None
    pickedItem
  }

  override def toString: String = {
    if (isVictoryTile) "V"
    else if (occupiedByPlayer) "P"
    else if (item.isDefined) "I"    
    else if (isBlocked) "+"    
    else "."             
  }
}
