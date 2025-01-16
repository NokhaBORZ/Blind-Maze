package de.htwg.se.blindmaze.model.tiles

import play.api.libs.json.{JsObject, Json}

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

  def toXml: scala.xml.Node = {
    <tile>
      {content match {
        case TileContent.Empty => <empty/>
        case TileContent.Wall => <wall/>
        case TileContent.Player(id) => <player>{id}</player>
        case TileContent.Victory => <victory/>
        case TileContent.OutOfBounds => <outOfBounds/>
        case TileContent.Trap => <trap/>
        case TileContent.ChestTile(chest) => <chest>{chest.item.toXml}</chest>
      }}
    </tile>
  }

  def toJson: JsObject = {
    Json.obj(
      "content" -> (content match {
        case TileContent.Empty => Json.toJson("empty")
        case TileContent.Wall => Json.toJson("wall")
        case TileContent.Player(id) => Json.obj("player" -> Json.obj("id" -> id))
        case TileContent.Victory => Json.toJson("victory")
        case TileContent.OutOfBounds => Json.toJson("outOfBounds")
        case TileContent.Trap => Json.toJson("trap")
        case TileContent.ChestTile(chest) => Json.toJson("chest")
      })
    )
  }
}

object Tile {
  def fromXml(node: scala.xml.Node): Tile = {
    val content = (node \ "_").headOption match {
      case Some(<empty/>) => TileContent.Empty
      case Some(<wall/>) => TileContent.Wall
      case Some(<player>{id}</player>) => TileContent.Player(id.text.toInt)
      case Some(<victory/>) => TileContent.Victory
      case Some(<outOfBounds/>) => TileContent.OutOfBounds
      case Some(<trap/>) => TileContent.Trap
      case Some(<chest>{item}</chest>) => TileContent.ChestTile(Chest(IItem.fromXml(item.head)))
      case _ => throw new IllegalArgumentException("Invalid XML: No content found")
    }
    Tile(content)
  }
}