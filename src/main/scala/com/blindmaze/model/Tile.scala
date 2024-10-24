package com.blindmaze.model

import com.blindmaze.math.Position
import com.blindmaze.model.Item

// Klasse Tile repräsentiert ein Feld auf dem Grid
class Tile(val position: Position, var item: Option[Item] = None, val isBlocked: Boolean = false) {

  // Methode, um ein Item vom Tile aufzuheben
  def pickUpItem(player: Player): Unit = {
    item match {
      case Some(i) =>
        player.collectItem(i)
        item = None // Entferne das Item vom Tile
        println(s"Player picked up: ${i.name}")
      case None =>
        println("No item to pick up")
    }
  }

  // Methode, um ein Item auf das Tile zu legen
  def placeItem(newItem: Item): Unit = {
    if (item.isEmpty) {
      item = Some(newItem)
      println(s"Item ${newItem.name} placed at position: ${position}")
    } else {
      println("Tile already contains an item")
    }
  }
}
