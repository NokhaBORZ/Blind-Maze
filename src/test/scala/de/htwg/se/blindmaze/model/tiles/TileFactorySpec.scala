package de.htwg.se.blindmaze.model.tiles

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.blindmaze.model.items.Item
import de.htwg.se.blindmaze.model.Player
import de.htwg.se.blindmaze.model.items.CursedMap

class TileFactorySpec extends AnyWordSpec with Matchers {

    "A TileFactory" should {

        "return an EmptyTile for TileContent.Empty" in {
            val tile = TileFactory.getTile(TileContent.Empty)
            tile should be(TileCache.EmptyTile)
        }

        "return a WallTile for TileContent.Wall" in {
            val tile = TileFactory.getTile(TileContent.Wall)
            tile should be(TileCache.WallTile)
        }

        "return a VictoryTile for TileContent.Victory" in {
            val tile = TileFactory.getTile(TileContent.Victory)
            tile should be(TileCache.VictoryTile)
        }

        "return a TrapTile for TileContent.Trap" in {
            val tile = TileFactory.getTile(TileContent.Trap)
            tile should be(TileCache.TrapTile)
        }

        "return a PlayerTile for TileContent.Player" in {
            val playerId = 1
            val tile = TileFactory.getTile(TileContent.Player(playerId))
            tile should be(TileCache.PlayerTile(Player(playerId)))
        }

    }
}