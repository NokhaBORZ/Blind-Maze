package de.htwg.se.blindmaze.model.tiles

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.blindmaze.model.item.IItem
import de.htwg.se.blindmaze.model.player.IPlayer
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar

class TileFactorySpec extends AnyWordSpec with Matchers with MockitoSugar {

    "A TileFactory" should {

        "return an EmptyTile when TileContent.Empty is passed" in {
            val tile = TileFactory.getTile(TileContent.Empty)
            tile should be(TileCache.EmptyTile)
        }

        "return a WallTile when TileContent.Wall(false) is passed" in {
            val tile = TileFactory.getTile(TileContent.Wall(visible = false))
            tile should be(TileCache.WallTile)
        }

        "return a visible WallTile when TileContent.Wall(true) is passed" in {
            val tile = TileFactory.getTile(TileContent.Wall(visible = true))
            tile should be(TileCache.visibleWallTile)
        }

        "return a VictoryTile when TileContent.Victory is passed" in {
            val tile = TileFactory.getTile(TileContent.Victory)
            tile should be(TileCache.VictoryTile)
        }

        "return a TrapTile when TileContent.Trap is passed" in {
            val tile = TileFactory.getTile(TileContent.Trap)
            tile should be(TileCache.TrapTile)
        }

        "return a PlayerTile when TileContent.Player(id) is passed" in {
            val playerId = 1
            val player = mock[IPlayer]
            when(player.id).thenReturn(playerId)
            val injector = TileFactory.injector
            val tile = TileFactory.getTile(TileContent.Player(playerId))
            tile should be(TileCache.PlayerTile(player))
        }

        "return a ChestTile when TileContent.ChestTile(chest) is passed" in {
            val item = mock[IItem]
            val chest = Chest(item)
            val tile = TileFactory.getTile(TileContent.ChestTile(chest))
            tile should be(TileCache.ChestTile(item))
        }
    }
}