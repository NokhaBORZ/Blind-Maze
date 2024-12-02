package de.htwg.se.blindmaze.model.items

import de.htwg.se.blindmaze.model.managers.GameManager

//Composite Pattern
case class Inventory(name: String, description: String = "") extends Item {
  private val items = scala.collection.mutable.ListBuffer[Item]()

    def addItem(item: Item): Unit = {
        items += item
    }

    def removeItem(item: Item): Unit = {
        items -= item
    }

    override def use(gameManager: GameManager): GameManager = ???

    // should return the rarity of the last item in the inventory
    override def rarity: Rarity = {
        items.last.rarity
    }
}