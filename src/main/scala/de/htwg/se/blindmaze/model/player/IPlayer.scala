package de.htwg.se.blindmaze.model.player

import de.htwg.se.blindmaze.model.item.IItem
import de.htwg.se.blindmaze.model.player.playerImp.Player

trait IPlayer {
  def id: Int
  def inventory: List[IItem]
  def addItem(item: IItem): IPlayer
  def removeItem(item: IItem): IPlayer
}

object IPlayer {
  def apply(id: Int, inventory: List[IItem] = List.empty): IPlayer = Player(id, inventory)
}