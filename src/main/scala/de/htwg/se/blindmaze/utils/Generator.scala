package de.htwg.se.blindmaze.utils

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
  private val random = new Random()

  def generateGrid(size: Int): IGrid = {
    var grid: IGrid = new Grid(size)
    
    // Fill the grid with walls
    for (x <- 0 until size; y <- 0 until size) {
      grid = grid.set(Position(x, y), Tile(TileContent.Wall(visible = false)))
    }

    // Generate the maze
    grid = generateMaze(grid, Position(1, 1))

    // Ensure the start and end positions are empty
    grid = grid.set(Position(0, 0), Tile(TileContent.Empty))
    grid = grid.set(Position(size - 1, size - 1), Tile(TileContent.Empty))

    // Place players
    grid = grid.set(Position(0, 0), Tile(TileContent.Player(1)))
    grid = grid.set(Position(size - 1, size - 1), Tile(TileContent.Player(2)))

    // Set the Victory Tile
    val center = Position(size / 2, size / 2)
    grid = grid.set(center, Tile(TileContent.Victory))

    // Ensure path to victory
    grid = ensurePathToVictory(grid, Position(0, 0), center)
    grid = ensurePathToVictory(grid, Position(size - 1, size - 1), center)

    // Add fake paths
    grid = addFakePaths(grid)

    // Add some random empty spaces
    grid = addRandomEmptySpaces(grid)

    println("Generated grid:\n")
    grid
  }

  private def generateMaze(grid: IGrid, start: Position): IGrid = {
    var updatedGrid = grid
    val stack = scala.collection.mutable.Stack[Position](start)
    val visited = scala.collection.mutable.Set[Position](start)

    while (stack.nonEmpty) {
      val current = stack.pop()
      val neighbors = getUnvisitedNeighbors(updatedGrid, current, visited)

      if (neighbors.nonEmpty) {
        stack.push(current)
        val next = neighbors(random.nextInt(neighbors.length))
        updatedGrid = carvePathBetween(updatedGrid, current, next)
        visited += next
        stack.push(next)
      }
    }

    updatedGrid
  }

  private def getUnvisitedNeighbors(grid: IGrid, pos: Position, visited: scala.collection.mutable.Set[Position]): List[Position] = {
    val directions = List(
      Position(0, -2), // Up
      Position(2, 0),  // Right
      Position(0, 2),  // Down
      Position(-2, 0)  // Left
    )

    directions
      .map(dir => Position(pos.x + dir.x, pos.y + dir.y))
      .filter(newPos => grid.inBounds(newPos) && !visited.contains(newPos))
  }

  private def carvePathBetween(grid: IGrid, from: Position, to: Position): IGrid = {
    var updatedGrid = grid
    updatedGrid = updatedGrid.set(from, Tile(TileContent.Empty))
    updatedGrid = updatedGrid.set(to, Tile(TileContent.Empty))
    val middle = Position((from.x + to.x) / 2, (from.y + to.y) / 2)
    updatedGrid = updatedGrid.set(middle, Tile(TileContent.Empty))
    updatedGrid
  }

  private def ensurePathToVictory(grid: IGrid, start: Position, goal: Position): IGrid = {
    var updatedGrid = grid
    var current = start

    while (current != goal) {
      val dx = goal.x - current.x
      val dy = goal.y - current.y

      if (random.nextBoolean() && dx.abs > 0 || dy.abs == 0) {
        current = Position(current.x + dx.sign, current.y)
      } else {
        current = Position(current.x, current.y + dy.sign)
      }

      if (updatedGrid.get(current).content == TileContent.Wall(visible = false)) {
        updatedGrid = updatedGrid.set(current, Tile(TileContent.Empty))
      }
    }

    updatedGrid
  }

  private def addFakePaths(grid: IGrid): IGrid = {
    var updatedGrid = grid
    val size = grid.size
    val numFakePaths = size / 2 // Adjust this number to control the number of fake paths

    for (_ <- 1 to numFakePaths) {
      val start = Position(random.nextInt(size), random.nextInt(size))
      val length = random.nextInt(size / 2) + 2 // Random length between 2 and size/2

      var current = start
      for (_ <- 1 to length) {
        if (updatedGrid.inBounds(current) && !updatedGrid.get(current).isAnyPlayer && updatedGrid.get(current).content != TileContent.Victory) {
          updatedGrid = updatedGrid.set(current, Tile(TileContent.Empty))
        }

        val direction = random.nextInt(4) match {
          case 0 => Position(0, -1) // Up
          case 1 => Position(1, 0)  // Right
          case 2 => Position(0, 1)  // Down
          case 3 => Position(-1, 0) // Left
        }
        current = Position(current.x + direction.x, current.y + direction.y)
      }
    }

    updatedGrid
  }

  private def addRandomEmptySpaces(grid: IGrid): IGrid = {
    var updatedGrid = grid
    val size = grid.size
    val numEmptySpaces = size * size / 10 // Adjust this number to control the number of random empty spaces

    for (_ <- 1 to numEmptySpaces) {
      val x = random.nextInt(size)
      val y = random.nextInt(size)
      val pos = Position(x, y)

      if (!updatedGrid.get(pos).isAnyPlayer && updatedGrid.get(pos).content != TileContent.Victory) {
        updatedGrid = updatedGrid.set(pos, Tile(TileContent.Empty))
      }
    }

    updatedGrid
  }
}

