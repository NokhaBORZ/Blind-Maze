import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.blindmaze.model.grid.gridImp.Grid
import de.htwg.se.blindmaze.model.player.IPlayer
import de.htwg.se.blindmaze.model.player.playerImp.Player
import de.htwg.se.blindmaze.utils.{Direction, Position}
import de.htwg.se.blindmaze.model.tiles.{Tile, TileContent}

class GridSpec extends AnyWordSpec with Matchers {

    "A Grid" should {

        "initialize with a given size" in {
            val grid = new Grid(4)
            grid.size shouldBe 4
        }

        "create a new grid with player positions and victory tile" in {
            val mockPlayer1 = Player(1)
            val mockPlayer2 = Player(2)
            val grid = new Grid(5).createGrid(List(mockPlayer1, mockPlayer2))
            grid.get(Position(0, 0)).content shouldBe TileContent.Player(1)
            grid.get(Position(4, 4)).content shouldBe TileContent.Player(2)
            grid.get(Position(2, 2)).content shouldBe TileContent.Victory
        }

        "allow setting and getting tiles" in {
            val grid = new Grid(2)
            val updated = grid.set(Position(1, 0), Tile(TileContent.Victory))
            updated.get(Position(1, 0)).content shouldBe TileContent.Victory
        }

        "return an OutOfBounds tile when queried out of range" in {
            val grid = new Grid(2)
            grid.get(Position(5, 5)).content shouldBe TileContent.OutOfBounds
        }

        "move a player correctly if possible" in {
            val player = Player(1)
            val grid = new Grid(3).set(Position(1,1), Tile(TileContent.Player(1)))
            val moved = grid.movePlayer(1, Direction.Up)
            moved.get(Position(1,0)).content shouldBe TileContent.Player(1)
        }

        "show whether a move is valid or not" in {
            val player = Player(2)
            val grid = new Grid(2).set(Position(0,0), Tile(TileContent.Player(2)))
            grid.canMove(2, Direction.Right) shouldBe true
            grid.canMove(2, Direction.Left) shouldBe false
        }

        "reveal all hidden walls" in {
            val grid = new Grid(2).set(Position(0,0), Tile(TileContent.Wall(false)))
            val revealed = grid.showAllWalls()
            revealed.get(Position(0,0)).content shouldBe TileContent.Wall(true)
        }

        "find a player's position if present" in {
            val player = Player(5)
            val grid = new Grid(2).set(Position(0,1), Tile(TileContent.Player(5)))
            grid.getPlayer(player) shouldBe Some(Position(0,1))
        }

        "correctly validate positions as in bounds" in {
            val grid = new Grid(2)
            grid.inBounds(Position(0,0)) shouldBe true
            grid.inBounds(Position(-1,2)) shouldBe false
        }
    }
}