package de.htwg.se.blindmaze.model.item.ItemsImp

import com.google.inject.Inject

import de.htwg.se.blindmaze.model.managers.IGameManager
import de.htwg.se.blindmaze.model.item.{IItem, Rarity}
import com.google.inject.name.Names
import com.google.inject.Guice
import com.google.inject.name.Named
case class Lantern @Inject() ( name: String) extends IItem {
    override def use(gameManager: IGameManager): IGameManager = ???
    override def rarity: Rarity = Rarity.Common
    val description: String = "A lantern that can light up the maze"
}