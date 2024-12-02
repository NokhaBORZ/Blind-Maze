package de.htwg.se.blindmaze.model

import de.htwg.se.blindmaze.model.items.Item

case class Player(id: Int, inventory: List[Item] = List.empty) {

  def addItem(item: Item): Player = copy(inventory = item :: inventory)

  def removeItem(item: Item): Player = copy(inventory = inventory.filter(_ != item))

  
}

