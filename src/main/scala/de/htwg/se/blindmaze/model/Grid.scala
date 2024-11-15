package de.htwg.se.blindmaze.model

import de.htwg.se.blindmaze.model.{Tile, TileContent, Position}
import de.htwg.se.blindmaze.utils.Renderer


case class Grid(tiles: Vector[Vector[Tile]]) {

  def this(size: Int) = this(Vector.fill(size, size)(Tile(TileContent.Empty)))

  def createGrid(playerList: List[Player]): Grid = {
    val initialGrid = new Grid(size)
    val updatedGrid = initialGrid
      .set(Position(0, 0), Tile(TileContent.Player(1)))
      .set(Position(size - 1, size - 1), Tile(TileContent.Player(2)))
    updatedGrid
  }

  def set(position: Position, tile: Tile): Grid = {
    copy(tiles.updated(position.y, tiles(position.y).updated(position.x, tile)))
  }

  def get(position: Position): Tile = {
    if (inBounds(position)) {
       tiles(position.y)(position.x)
    } else {
       Tile(TileContent.OutOfBounds)
    }
  }

  def size: Int = tiles.size

  def movePlayer(playerId: Int, direction: Direction): Grid = {
  val playerPosition = getPlayer(Player(playerId))
  val newPosition = playerPosition.move(direction)
  println(newPosition)
  

  if (inBounds(newPosition)) {
    set(playerPosition, Tile(TileContent.Empty))
    .set(newPosition, Tile(TileContent.Player(playerId)))
  } else {
    this
  }
  }

  private def getPlayer(player: Player): Position = {
  tiles.zipWithIndex.flatMap { case (row, y) =>
    row.zipWithIndex.collect {
    case (tile, x) if tile.content == TileContent.Player(player.id) => Position(x, y)
    }
  }.headOption.getOrElse(Position(-1, -1))
  }

  private def inBounds(position: Position): Boolean = {
    position.x >= 0 && position.y >= 0 && position.x < size && position.y < size
  }

  def showGrid(): String = {
    Renderer.render(this)
  }
}
