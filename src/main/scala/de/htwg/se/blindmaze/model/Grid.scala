package de.htwg.se.blindmaze.model

import de.htwg.se.blindmaze.math.{Direction, Position}

class Grid(val size: Int) {
  
  val tiles: Array[Array[Tile]] = generateMaze()

  private def generateMaze(): Array[Array[Tile]] = {
    Array.tabulate(size, size)((x, y) => new Tile(Position(x, y), isBlocked = scala.util.Random.nextBoolean()))
  }

  def placeItem(item: Item, position: Position): Unit = {
    getTile(position) match {
      case Some(tile) => tile.placeItem(item)
      case None => println(s"Position $position is out of bounds.")
    }
  }

   def getTile(position: Position): Option[Tile] = {
    if (isValidPosition(position)) Some(tiles(position.x)(position.y))
    else None
  }

  def placeItemsRandomly(items: List[Item], probability: Double): Unit = {
    for (item <- items) {
      val x = scala.util.Random.nextInt(size)
      val y = scala.util.Random.nextInt(size)
      if (scala.util.Random.nextDouble() <= probability) {
        getTile(Position(x, y)).foreach(_.placeItem(item))
      }
    }
  }

  def movePlayer(player: Player, direction: Direction): Boolean = {
    val newPosition = direction match {
      case Direction.Up    => player.position.copy(y = player.position.y - 1)
      case Direction.Down  => player.position.copy(y = player.position.y + 1)
      case Direction.Left  => player.position.copy(x = player.position.x - 1)
      case Direction.Right => player.position.copy(x = player.position.x + 1)
    }

    if (isValidMove(newPosition)) {
      player.move(direction, this)
      println(s"Player moved to: $newPosition")
      true
    } else {
      println("Invalid move. Tile is blocked or out of bounds.")
      false
    }
  }

  def findPath(start: Position, goal: Position): List[Position] = {
    List()
  }

  def toggleTileBlocked(position: Position, blocked: Boolean): Unit = {
    getTile(position).foreach(_.isBlocked = blocked)
  }

  def isValidMove(position: Position): Boolean = 
    isValidPosition(position) && getTile(position).exists(!_.isBlocked)

  private def isValidPosition(position: Position): Boolean = 
    position.x >= 0 && position.x < size && position.y >= 0 && position.y < size

  def wrapPosition(position: Position): Position = {
    val newX = (position.x + size) % size
    val newY = (position.y + size) % size
    Position(newX, newY)
  }
}
