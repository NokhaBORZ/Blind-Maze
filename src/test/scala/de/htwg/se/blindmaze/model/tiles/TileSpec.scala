package de.htwg.se.blindmaze.model.tiles

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.blindmaze.model.item.IItem
import de.htwg.se.blindmaze.model.player.IPlayer
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar

class TileSpec extends AnyWordSpec with Matchers with MockitoSugar {

    "A Tile" should {

        "be walkable if it is empty" in {
            val tile = Tile(TileContent.Empty)
            tile.isWalkable should be(true)
        }

        "be walkable if it is a victory tile" in {
            val tile = Tile(TileContent.Victory)
            tile.isWalkable should be(true)
        }

        "not be walkable if it is a wall" in {
            val tile = Tile(TileContent.Wall(visible = false))
            tile.isWalkable should be(false)
        }

        "contain a player if it has player content" in {
            val player = mock[IPlayer]
            when(player.id).thenReturn(1)
            val tile = Tile(TileContent.Player(player.id))
            tile.hasPlayer should be(true)
        }

        "not contain a player if it has no player content" in {
            val tile = Tile(TileContent.Empty)
            tile.hasPlayer should be(false)
        }

        "identify the correct player" in {
            val player = mock[IPlayer]
            when(player.id).thenReturn(1)
            val tile = Tile(TileContent.Player(player.id))
            tile.isPlayer(player) should be(true)
        }

        "not identify an incorrect player" in {
            val player1 = mock[IPlayer]
            when(player1.id).thenReturn(1)
            val player2 = mock[IPlayer]
            when(player2.id).thenReturn(2)
            val tile = Tile(TileContent.Player(player1.id))
            tile.isPlayer(player2) should be(false)
        }

        "contain a chest if it has chest content" in {
            val item = mock[IItem]
            val chest = Chest(item)
            val tile = Tile(TileContent.ChestTile(chest))
            tile.hasChest should be(true)
        }

        "not contain a chest if it has no chest content" in {
            val tile = Tile(TileContent.Empty)
            tile.hasChest should be(false)
        }

        "be a trap if it has trap content" in {
            val tile = Tile(TileContent.Trap)
            tile.isTrap should be(true)
        }

        "not be a trap if it has no trap content" in {
            val tile = Tile(TileContent.Empty)
            tile.isTrap should be(false)
        }

        "serialize to XML correctly with pretty printer" in {
            val tile = Tile(TileContent.Player(1))
            val xml = tile.toXml
            val prettyPrinter = new scala.xml.PrettyPrinter(80, 2)
            val prettyXml = prettyPrinter.format(xml)
            prettyXml should be("<tile>\n  <player>1</player>\n</tile>")
        }

        "deserialize from XML correctly" in {
            val xml = <tile><player>1</player></tile>
            val tile = Tile.fromXml(xml)
            tile.content.should(be(TileContent.Player(1)))
        }

        "serialize to JSON correctly" in {
            val tile = Tile(TileContent.Player(1))
            val json = tile.toJson
            (json \ "content" \ "player" \ "id").as[Int] should be(1)
        }
    }
}
