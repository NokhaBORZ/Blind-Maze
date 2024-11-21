package de.htwg.se.blindmaze.model

case class Player(id: Int, inventory: List[Item] = List.empty) {

  def addItem(item: Item): Player = copy(inventory = item :: inventory)

  def removeItem(item: Item): Player = copy(inventory = inventory.filter(_ != item))
  
}

