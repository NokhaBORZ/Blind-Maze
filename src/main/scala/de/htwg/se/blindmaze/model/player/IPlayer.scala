package de.htwg.se.blindmaze.model.player

import play.api.libs.json.Json

import de.htwg.se.blindmaze.model.item.IItem
import de.htwg.se.blindmaze.model.player.playerImp.Player
import play.api.libs.json.JsObject

trait IPlayer {
  def id: Int
  def inventory: List[IItem]
  def addItem(item: IItem): IPlayer
  def removeItem(item: IItem): IPlayer

  def toXml: scala.xml.Node = {
    <player>
      <id>{id}</id>
      <inventory>
        {inventory.map(_.toXml)}
      </inventory>
    </player>
  }

  def toJson: JsObject = {
    Json.obj(
      "id" -> id,
      "inventory" -> inventory.map(_.toJson)
    )
  }
}
