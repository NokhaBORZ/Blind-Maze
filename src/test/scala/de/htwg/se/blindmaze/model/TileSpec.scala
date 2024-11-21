package de.htwg.se.blindmaze.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TileSpec extends AnyWordSpec with Matchers {

    "A Tile" should {

        "be walkable if it is empty" in {
            val tile = Tile(TileContent.Empty)
            tile.isWalkable should be(true)
        }

        "not be walkable if it is a wall" in {
            val tile = Tile(TileContent.Wall)
            tile.isWalkable should be(false)
        }

        "contain a player if it has a player" in {
            val player = Player(1)
            val tile = Tile(TileContent.Player(player.id))
            tile.isPlayer(player) should be(true)
        }

        "not contain a player if it is empty" in {
            val tile = Tile(TileContent.Empty)
            tile.hasPlayer should be(false)
        }

        "identify the correct player" in {
            val player = Player(1)
            val tile = Tile(TileContent.Player(player.id))
            tile.isPlayer(player) should be(true)
        }

        "not identify a different player" in {
            val player1 = Player(1)
            val player2 = Player(2)
            val tile = Tile(TileContent.Player(player1.id))
            tile.isPlayer(player2) should be(false)
        }

        "contain a chest if it has a chest" in {
            val item = Map
            val tile = Tile(TileContent.ChestTile(Chest(item)))
            tile.hasChest should be(true)
        }

        "not contain a chest if it is empty" in {
            val tile = Tile(TileContent.Empty)
            tile.hasChest should be(false)
        }

        "be a trap if it is a trap" in {
            val tile = Tile(TileContent.Trap)
            tile.isTrap should be(true)
        }

        "not be a trap if it is empty" in {
            val tile = Tile(TileContent.Empty)
            tile.isTrap should be(false)
        }
    }

    "Tile object" should {

        "create an empty tile" in {
            val tile = Tile.EmptyTile
            tile.content should be(TileContent.Empty)
        }

        "create a wall tile" in {
            val tile = Tile.WallTile
            tile.content should be(TileContent.Wall)
        }

        "create a player tile" in {
            val player = Player(1)
            val tile = Tile.PlayerTile(player)
            tile.content should be(TileContent.Player(player.id))
        }

        "create a victory tile" in {
            val tile = Tile.VictoryTile
            tile.content should be(TileContent.Victory)
        }

        "create a trap tile" in {
            val tile = Tile.TrapTile
            tile.content should be(TileContent.Trap)
        }

        "create a chest tile" in {
            val item = Map
            val tile = Tile.ChestTile(item)
            tile.content should be(TileContent.ChestTile(Chest(item)))
        }
    }
}