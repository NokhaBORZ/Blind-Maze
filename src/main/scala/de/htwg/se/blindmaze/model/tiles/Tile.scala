package de.htwg.se.blindmaze.model.tiles

import de.htwg.se.blindmaze.model.player.IPlayer
import de.htwg.se.blindmaze.model.item.IItem


//Flyweight Pattern

case class Chest(item : IItem)

sealed trait TileContent
object TileContent {
  case object Empty extends TileContent
  case object Wall extends TileContent
  case class Player(val id: Int) extends TileContent
  case object Victory extends TileContent
  case object OutOfBounds extends TileContent
  case object Trap extends TileContent
  case class ChestTile(chest: Chest) extends TileContent
}


case class Tile(content: TileContent) {

  def isWalkable: Boolean = content match {
    case TileContent.Empty => true
    case _                 => false
  }

  def hasPlayer: Boolean = content match {
    case TileContent.Player(_) => true
    case _ => false
  }

  def isPlayer(player: IPlayer): Boolean = content match {
    case TileContent.Player(id) => id == player.id
    case _                      => false
  }

  def hasChest: Boolean = content match {
    case TileContent.ChestTile(_) => true
    case _ => false
  }

  def isTrap: Boolean = content == TileContent.Trap

}

