package com.blindmaze.model

import com.blindmaze.math.Position
import com.blindmaze.math.Direction
import com.blindmaze.model.Item

class Player(var position: Position) {
  
  // Liste von Items, die der Spieler gesammelt hat
  var inventory: List[Item] = List()

  // Methode, um den Spieler in eine bestimmte Richtung zu bewegen
  def move(direction: Direction.Value): Unit = {
    direction match {
      case Direction.Up => position = Position(position.x, position.y - 1)
      case Direction.Down => position = Position(position.x, position.y + 1)
      case Direction.Left => position = Position(position.x - 1, position.y)
      case Direction.Right => position = Position(position.x + 1, position.y)
    }
    println(s"Player moved to position: ${position}")
  }

  // Methode, um ein Item zu benutzen
  def useItem(item: Item): Unit = {
    if (inventory.contains(item)) {
      item.applyEffect(this)
      inventory = inventory.filterNot(_ == item) // Entferne das benutzte Item aus dem Inventar
      println(s"Used item: ${item.name}")
    } else {
      println(s"Item ${item.name} is not in the inventory")
    }
  }

  // Methode, um ein Item zu sammeln
  def collectItem(item: Item): Unit = {
    inventory = item :: inventory
    println(s"Collected item: ${item.name}")
  }
}
