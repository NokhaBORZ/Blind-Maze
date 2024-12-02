package de.htwg.se.blindmaze.model.tiles

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.blindmaze.model.items.Item
import de.htwg.se.blindmaze.model.Player
import de.htwg.se.blindmaze.model.items.CursedMap

class TileCacheSpec extends AnyWordSpec with Matchers {

    "A TileCache" should {

        "provide an EmptyTile" in {
            TileCache.EmptyTile.content should be(TileContent.Empty)
        }

        "provide a WallTile" in {
            TileCache.WallTile.content should be(TileContent.Wall)
        }

        "provide a VictoryTile" in {
            TileCache.VictoryTile.content should be(TileContent.Victory)
        }

        "provide a TrapTile" in {
            TileCache.TrapTile.content should be(TileContent.Trap)
        }

        "provide a PlayerTile" in {
            val player = Player(1)
            val playerTile = TileCache.PlayerTile(player)
            playerTile.content should be(TileContent.Player(player.id))
        }

        "provide a ChestTile" in {
            val item = CursedMap("item" , "show 3 tiles of the way")
            val chestTile = TileCache.ChestTile(item)
            chestTile.content should be(TileContent.ChestTile(Chest(item)))
        }
    }
}