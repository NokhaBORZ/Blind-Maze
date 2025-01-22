package de.htwg.se.blindmaze.model.tiles

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.blindmaze.model.item.IItem
import de.htwg.se.blindmaze.model.player.IPlayer

class TileCacheSpec extends AnyWordSpec with Matchers {

    "TileCache" should {

        "provide a singleton instance of EmptyTile" in {
            TileCache.EmptyTile shouldBe a [Tile]
            TileCache.EmptyTile.content shouldBe TileContent.Empty
        }

        "provide a singleton instance of WallTile" in {
            TileCache.WallTile shouldBe a [Tile]
            TileCache.WallTile.content shouldBe TileContent.Wall(visible = false)
        }

        "provide a singleton instance of visibleWallTile" in {
            TileCache.visibleWallTile shouldBe a [Tile]
            TileCache.visibleWallTile.content shouldBe TileContent.Wall(visible = true)
        }

        "provide a singleton instance of VictoryTile" in {
            TileCache.VictoryTile shouldBe a [Tile]
            TileCache.VictoryTile.content shouldBe TileContent.Victory
        }

        "provide a singleton instance of TrapTile" in {
            TileCache.TrapTile shouldBe a [Tile]
            TileCache.TrapTile.content shouldBe TileContent.Trap
        }
    }
}