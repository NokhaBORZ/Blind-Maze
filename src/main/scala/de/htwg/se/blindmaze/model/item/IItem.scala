package de.htwg.se.blindmaze.model.item

import play.api.libs.json.{JsObject, Json}

import de.htwg.se.blindmaze.model.managers.IGameManager
import de.htwg.se.blindmaze.model.item.ItemsImp.Inventory

/**
 * Enumeration representing the rarity of an item.
 */
enum Rarity:
  case Common, Rare, Epic, Legendary

/**
 * Trait representing a generic item in the game.
 */
trait IItem {
  /**
   * The name of the item.
   */
  val name: String

  /**
   * Method to use the item, which modifies the game state.
   * 
   * @param IGameManager The current game manager instance.
   * @return The updated game manager instance after using the item.
   */
  def use(IGameManager: IGameManager): IGameManager

  /**
   * The rarity of the item.
   */
  def rarity: Rarity

  /**
   * A description of the item.
   */
  def description: String

  def toXml: scala.xml.Node = {
    <item>
      <name>{name}</name>
    </item>
  }

  def toJson: JsObject = {
    Json.obj(
      "name" -> name
    )
  }
}

object IItem {
  def fromXml(node: scala.xml.Node): IItem = {
    val name = (node \ "name").text
    Inventory(name)
  }

  def fromJson(json: play.api.libs.json.JsValue): IItem = {
    val name = (json \ "name").as[String]
    Inventory(name)
  }
}
