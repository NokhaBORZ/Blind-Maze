package de.htwg.se.blindmaze.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.blindmaze.model.tiles.{Tile, TileFactory, TileContent}

class GridSpec extends AnyWordSpec with Matchers {

    "A Grid" should {

        "be created with a given size" in {
            val grid = new Grid(3)
            grid.size should be(3)
        }

        "set a tile at a specific position" in {
            val grid = new Grid(3)
            val newGrid = grid.set(Position(1, 1), TileFactory.getTile(TileContent.Player(1)))
            newGrid.get(Position(1, 1)).content should be(TileContent.Player(1))
        }

        "get a tile at a specific position" in {
            val grid = new Grid(3)
            grid.get(Position(0, 0)).content should be(TileContent.Empty)
        }

        "return OutOfBounds tile for an invalid position" in {
            val grid = new Grid(3)
            grid.get(Position(-1, -1)).content should be(TileContent.OutOfBounds)
        }

        "move a player to a new position if the target tile is empty" in {
            val grid = new Grid(3).set(Position(0, 0), TileFactory.getTile(TileContent.Player(1)))
            val newGrid = grid.movePlayer(1, de.htwg.se.blindmaze.utils.Direction.Right)
            newGrid.get(Position(0, 0)).content should be(TileContent.Empty)
            newGrid.get(Position(1, 0)).content should be(TileContent.Player(1))
        }

        "not move a player to a new position if the target tile is not empty" in {
            val grid = new Grid(3)
                .set(Position(0, 0), TileFactory.getTile(TileContent.Player(1)))
                .set(Position(1, 0), TileFactory.getTile(TileContent.Player(2)))
            val newGrid = grid.movePlayer(1, de.htwg.se.blindmaze.utils.Direction.Right)
            newGrid.get(Position(0, 0)).content should be(TileContent.Player(1))
            newGrid.get(Position(1, 0)).content should be(TileContent.Player(2))
        }

        "check if a player can move to a new position" in {
            val grid = new Grid(3).set(Position(0, 0), TileFactory.getTile(TileContent.Player(1)))
            grid.canMove(1, de.htwg.se.blindmaze.utils.Direction.Right) should be(true)
            grid.canMove(1, de.htwg.se.blindmaze.utils.Direction.Left) should be(false)
        }

        "get the position of a player" in {
            val grid = new Grid(3).set(Position(1, 1), TileFactory.getTile(TileContent.Player(1)))
            grid.getPlayer(Player(1)) should be(Position(1, 1))
        }

        "return an invalid position if the player is not found" in {
            val grid = new Grid(3)
            grid.getPlayer(Player(1)) should be(Position(-1, -1))
        }

        "check if a position is within bounds" in {
            val grid = new Grid(3)
            grid.inBounds(Position(1, 1)) should be(true)
            grid.inBounds(Position(-1, -1)) should be(false)
        }

        "show the grid as a string" in {
            val grid = new Grid(3)
            grid.showGrid() should not be empty
        }
    }
}