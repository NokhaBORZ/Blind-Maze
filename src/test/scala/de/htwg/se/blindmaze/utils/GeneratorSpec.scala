package de.htwg.se.blindmaze.utils

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar
import de.htwg.se.blindmaze.model.grid.IGrid
import de.htwg.se.blindmaze.model.grid.gridImp.Grid
import de.htwg.se.blindmaze.model.tiles.TileContent
import de.htwg.se.blindmaze.utils.Position

class GeneratorSpec extends AnyWordSpec with Matchers with MockitoSugar {

    "A Generator" should {

        "generate a grid of the correct size" in {
            val size = 11
            val grid = Generator.generateGrid(size)
            grid.size should be(size)
        }

        "place players at the correct positions" in {
            val size = 11
            val grid = Generator.generateGrid(size)
            grid.get(Position(0, 0)).content should be(TileContent.Player(1))
            grid.get(Position(size - 1, size - 1)).content should be(TileContent.Player(2))
        }

        "set the victory tile at the center" in {
            val size = 11
            val grid = Generator.generateGrid(size)
            val center = Position(size / 2, size / 2)
            grid.get(center).content should be(TileContent.Victory)
        }


        "add fake paths to the grid" in {
            val size = 11
            val grid = Generator.generateGrid(size)
            // Check that there are some empty tiles that are not part of the main path
            val emptyTiles = for {
                x <- 0 until size
                y <- 0 until size
                if grid.get(Position(x, y)).content == TileContent.Empty
            } yield Position(x, y)
            emptyTiles.size should be > (size * size / 10)
        }

        "add random empty spaces to the grid" in {
            val size = 11
            val grid = Generator.generateGrid(size)
            // Check that there are some random empty spaces
            val emptyTiles = for {
                x <- 0 until size
                y <- 0 until size
                if grid.get(Position(x, y)).content == TileContent.Empty
            } yield Position(x, y)
            emptyTiles.size should be > (size * size / 10)
        }
    }
}