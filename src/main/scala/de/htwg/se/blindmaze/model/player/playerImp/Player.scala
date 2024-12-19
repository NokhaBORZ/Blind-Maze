package de.htwg.se.blindmaze.model.player.playerImp

import de.htwg.se.blindmaze.model.item.IItem
import de.htwg.se.blindmaze.model.player.IPlayer

case class Player(id: Int, inventory: List[IItem] = List.empty) extends IPlayer {

  def addItem(item: IItem): Player = copy(inventory = item :: inventory)

  def removeItem(item: IItem): Player = copy(inventory = inventory.filter(_ != item))

  
}

