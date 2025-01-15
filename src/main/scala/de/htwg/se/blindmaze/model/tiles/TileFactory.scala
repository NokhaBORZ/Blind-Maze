package de.htwg.se.blindmaze.model.tiles

import de.htwg.se.blindmaze.model.item.IItem
import de.htwg.se.blindmaze.model.player.IPlayer
import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.blindmaze.modules.AppModule

//factory pattern

object TileFactory {
  private val playerTiles = scala.collection.mutable.Map[Int, Tile]()
  private val chestTiles = scala.collection.mutable.Map[IItem, Tile]()
  val injector = Guice.createInjector(new AppModule)

  def getTile(content: TileContent): Tile = content match {
    case TileContent.Empty => TileCache.EmptyTile
    case TileContent.Wall => TileCache.WallTile
    case TileContent.Victory => TileCache.VictoryTile
    case TileContent.Trap => TileCache.TrapTile
    case TileContent.Player(id) =>
      playerTiles.getOrElseUpdate(id, TileCache.PlayerTile(injector.instance[IPlayer](Names.named(id.toString))))
    case TileContent.ChestTile(chest) =>
      chestTiles.getOrElseUpdate(chest.item, TileCache.ChestTile(chest.item))
    case _ => Tile(content)
  }
}