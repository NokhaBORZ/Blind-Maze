package de.htwg.se.blindmaze.model.grid.gridImp

import com.google.inject.Inject
import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.blindmaze.modules.AppModule

import de.htwg.se.blindmaze.model.tiles.{Tile, TileFactory, TileContent}
import de.htwg.se.blindmaze.utils.TUIrenderer
import de.htwg.se.blindmaze.utils.Direction
import de.htwg.se.blindmaze.utils.Position
import de.htwg.se.blindmaze.model.player.IPlayer
import de.htwg.se.blindmaze.model.grid.IGrid

//Flyweight pattern

case class Grid @Inject()(tiles: Vector[Vector[Tile]]) extends IGrid {

  val injector = Guice.createInjector(new AppModule)

  def this(size: Int) = this(Vector.fill(size, size)(TileFactory.getTile(TileContent.Empty)))

  def createGrid(playerList: List[IPlayer]): Grid = {
    val initialGrid = new Grid(size)
    val updatedGrid = initialGrid
      .set(Position(0, 0), TileFactory.getTile(TileContent.Player(playerList.head.id)))
      .set(Position(size - 1, size - 1), TileFactory.getTile(TileContent.Player(playerList(1).id)))
    updatedGrid
  }

  def set(position: Position, tile: Tile): Grid = {
    copy(tiles.updated(position.y, tiles(position.y).updated(position.x, tile)))
  }

  def get(position: Position): Tile = {
    if (inBounds(position)) {
       tiles(position.y)(position.x)
    } else {
       TileFactory.getTile(TileContent.OutOfBounds)
    }
  }

  def size: Int = tiles.size

  def movePlayer(playerId: Int, direction: Direction): Grid = {
    val playerPosition = getPlayer(injector.instance[IPlayer](Names.named(playerId.toString()))) match{
      case Some(position) => position
      case None => return this
    }
    val newPosition = playerPosition.move(direction)

    if (inBounds(newPosition)) {
      val targetTile = get(newPosition)
      targetTile.content match {
        case TileContent.Empty =>
          set(playerPosition, Tile(TileContent.Empty))
            .set(newPosition, Tile(TileContent.Player(playerId)))
        case _ =>
          this
      }
    } else {
        this
    }
  }

  def canMove(playerId: Int, direction: Direction): Boolean = {
    val playerPosition = getPlayer(injector.instance[IPlayer](Names.named(playerId.toString()))) match{
      case Some(position) => position
      case None => return false
    }
    val newPosition = playerPosition.move(direction)

    if (inBounds(newPosition)) {
      val targetTile = get(newPosition)
      targetTile.content match {
        case TileContent.OutOfBounds => false
        case _ => true
      }
    } else {
      false
    }
  }

  def getPlayer(player: IPlayer): Option[Position] = {
  tiles.zipWithIndex.flatMap { case (row, y) =>
    row.zipWithIndex.collect {
    case (tile, x) if tile.content == TileContent.Player(player.id) => Position(x, y)
    }
  }.headOption
  }

  def inBounds(position: Position): Boolean = {
    position.x >= 0 && position.y >= 0 && position.x < size && position.y < size
  }

  def showGrid(): String = {
    TUIrenderer.render(this)
  }
}
