package de.htwg.se.blindmaze.model.tiles

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.blindmaze.model.items.Item
import de.htwg.se.blindmaze.model.Player
import de.htwg.se.blindmaze.model.items.CursedMap
import de.htwg.se.blindmaze.model.items.CursedMap

class TileSpec extends AnyWordSpec with Matchers {

    "A Tile" should {

        "be walkable if it is empty" in {
            val tile = Tile(TileContent.Empty)
            tile.isWalkable should be(true)
        }

        "not be walkable if it is not empty" in {
            val tile = Tile(TileContent.Wall)
            tile.isWalkable should be(false)
        }

        "not have a player if it is empty" in {
            val tile = Tile(TileContent.Empty)
            tile.hasPlayer should be(false)
        }

        "have a player if it contains a player" in {
            val tile = Tile(TileContent.Player(1))
            tile.hasPlayer should be(true)
        }

        "identify the correct player" in {
            val player = Player(1)
            val tile = Tile(TileContent.Player(1))
            tile.isPlayer(player) should be(true)
        }

        "not identify a different player" in {
            val player = Player(1)
            val tile = Tile(TileContent.Player(2))
            tile.isPlayer(player) should be(false)
        }

        "not have a chest if it is empty" in {
            val tile = Tile(TileContent.Empty)
            tile.hasChest should be(false)
        }

        "have a chest if it contains a chest" in {
            val chest = Chest(item = CursedMap("item" , "show 3 tiles of the way"))
            val tile = Tile(TileContent.ChestTile(chest))
            tile.hasChest should be(true)
        }

        "be a trap if it contains a trap" in {
            val tile = Tile(TileContent.Trap)
            tile.isTrap should be(true)
        }

        "not be a trap if it does not contain a trap" in {
            val tile = Tile(TileContent.Empty)
            tile.isTrap should be(false)
        }
    }
}