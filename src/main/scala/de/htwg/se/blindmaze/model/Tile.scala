package de.htwg.se.blindmaze.model

case class Chest(item : Item)

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

  def hasPlayer: Boolean = content == TileContent.Player

  def isPlayer(player: Player): Boolean = content match {
    case TileContent.Player(id) => id == player.id
    case _                      => false
  }

  def hasChest: Boolean = content match {
    case TileContent.ChestTile(_) => true
    case _ => false
  }

  def isTrap: Boolean = content == TileContent.Trap

}

object Tile {
  def EmptyTile: Tile = Tile(TileContent.Empty)
  def WallTile: Tile = Tile(TileContent.Wall)
  def PlayerTile(player: Player): Tile = Tile(TileContent.Player(player.id))
  def VictoryTile: Tile = Tile(TileContent.Victory)
  def TrapTile: Tile = Tile(TileContent.Trap)
  def ChestTile(item: Item): Tile = Tile(TileContent.ChestTile(Chest(item)))
}
