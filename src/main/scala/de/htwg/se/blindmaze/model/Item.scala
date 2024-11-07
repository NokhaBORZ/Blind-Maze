package de.htwg.se.blindmaze.model

import de.htwg.se.blindmaze.model.Player

// Abstrakte Klasse für Items
abstract class Item(val name: String) {
  def applyEffect(player: Player): Unit
}

class coin extends Item("Coin") {
  override def applyEffect(player: Player): Unit = {
    println("Player got 1 coin")
  }
}