package utils

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._

import de.htwg.se.blindmaze.modules.AppModule
import de.htwg.se.blindmaze.model.tiles.{Tile, TileContent}
import de.htwg.se.blindmaze.utils.Position
import de.htwg.se.blindmaze.model.grid.IGrid
import de.htwg.se.blindmaze.model.grid.gridImp.Grid
import scala.util.Random

object Generator {
  private val injector = Guice.createInjector(new AppModule)

  def generateGrid(size: Int): IGrid = {
    // Step 1: Fill the grid with walls
    var grid: IGrid = new Grid(size)
    for (x <- 0 until size; y <- 0 until size) {
      grid = grid.set(Position(x, y), Tile(TileContent.Wall))
    }

    // Step 2: Spawn players in opposite corners
    grid = grid
      .set(Position(0, 0), Tile(TileContent.Player(1)))
      .set(Position(size - 1, size - 1), Tile(TileContent.Player(2)))

    // Step 3: Create paths from players to the center
    val center = Position(size / 2, size / 2)
    grid = generatePath(grid, Position(0, 0), center) // Player 1's path
    grid = generatePath(grid, Position(size - 1, size - 1), center) // Player 2's path

    // Step 4: Set the center as the Victory Tile
    grid = grid.set(center, Tile(TileContent.Victory))

    println("Generated grid:\n" + grid.showGrid())
    grid
  }

  private def generatePath(grid: IGrid, start: Position, goal: Position): IGrid = {
    val random = new Random()
    var current = start
    var updatedGrid = grid
    val visited = scala.collection.mutable.Set[Position](start)
    val stack = scala.collection.mutable.Stack[Position](start)

    while (stack.nonEmpty) {
        current = stack.pop()
        if (current == goal) return updatedGrid

        // Determine valid moves
        val directions = random.shuffle(List(
        Position(0, -1), // Up
        Position(1, 0),  // Right
        Position(0, 1),  // Down
        Position(-1, 0)  // Left
        ))

        val validMoves = directions
        .map(dir => Position(current.x + dir.x, current.y + dir.y))
        .filter(pos => updatedGrid.inBounds(pos) && !visited.contains(pos) && !updatedGrid.get(pos).isAnyPlayer)

        // Process valid moves
        if (validMoves.nonEmpty) {
        stack.push(current) // Backtrack point
        val next = validMoves.head
        updatedGrid = updatedGrid.set(next, Tile(TileContent.Empty))
        visited += next
        stack.push(next)
        }
    }

    throw new RuntimeException(s"Failed to generate path from $start to $goal: No valid moves available")
    }

}