package de.htwg.se.blindmaze.model.item.ItemsImp

import de.htwg.se.blindmaze.model.managers.IGameManager
import de.htwg.se.blindmaze.model.item.{IItem, Rarity}

case class Lantern(name: String, description: String = "") extends IItem {
    override def use(gameManager: IGameManager): IGameManager = ???
    override def rarity: Rarity = Rarity.Common
}