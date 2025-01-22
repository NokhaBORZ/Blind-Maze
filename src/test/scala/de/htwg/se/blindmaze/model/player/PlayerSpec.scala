package de.htwg.se.blindmaze.model.player

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.blindmaze.model.item.IItem
import de.htwg.se.blindmaze.model.player.playerImp.Player
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar

class PlayerSpec extends AnyWordSpec with Matchers with MockitoSugar {

    "A Player" should {

        "add an item to the inventory" in {
            val player = Player(1)
            val item = mock[IItem]
            val updatedPlayer = player.addItem(item)
            updatedPlayer.inventory should contain(item)
        }

        "remove an item from the inventory" in {
            val item = mock[IItem]
            val player = Player(1, List(item))
            val updatedPlayer = player.removeItem(item)
            updatedPlayer.inventory should not contain item
        }

        "not remove an item that is not in the inventory" in {
            val item1 = mock[IItem]
            val item2 = mock[IItem]
            val player = Player(1, List(item1))
            val updatedPlayer = player.removeItem(item2)
            updatedPlayer.inventory should contain(item1)
            updatedPlayer.inventory should not contain item2
        }

        "serialize to XML" in {
            val item = mock[IItem]
            when(item.toXml).thenReturn(<item><name>Test Item</name></item>)
            val player = Player(1, List(item))
            val xml = player.toXml
            val prettyPrinter = new scala.xml.PrettyPrinter(80, 2)
            prettyPrinter.format(xml) should be(prettyPrinter.format(<player><id>1</id><inventory><item><name>Test Item</name></item></inventory></player>))
        }

        "serialize to JSON" in {
            val item = mock[IItem]
            when(item.toJson).thenReturn(play.api.libs.json.Json.obj("name" -> "Test Item"))
            val player = Player(1, List(item))
            val json = player.toJson
            json should be(play.api.libs.json.Json.obj("id" -> 1, "inventory" -> play.api.libs.json.Json.arr(play.api.libs.json.Json.obj("name" -> "Test Item"))))
        }
    }
}