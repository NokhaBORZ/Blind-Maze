package de.htwg.se.blindmaze.model.item.ItemsImp

import play.api.libs.json.{JsObject, Json}

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

    override def toXml: scala.xml.Node = {
        <inventory>
            <name>{name}</name>
            {items.map(_.toXml)}
        </inventory>
    }

    override def toJson: JsObject = {
        Json.obj(
            "name" -> name,
            "items" -> items.map(_.toJson)
        )
    }
}