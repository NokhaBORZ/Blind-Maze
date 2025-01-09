package de.htwg.se.blindmaze.model.item.ItemsImp

import de.htwg.se.blindmaze.model.managers.IGameManager
import de.htwg.se.blindmaze.model.item.{IItem, Rarity}


//Composite Pattern
case class Inventory(name: String, description: String = "") extends IItem {
   val items = scala.collection.mutable.ListBuffer[IItem]()

    def addItem(item: IItem): Unit = {
        items += item
    }

    def removeItem(item: IItem): Unit = {
        items -= item
    }

    override def use(gameManager: IGameManager): IGameManager = ???

    // should return the rarity of the last item in the inventory
    override def rarity: Rarity = {
        items.last.rarity
    }
}