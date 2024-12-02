package de.htwg.se.blindmaze.model.tiles

import de.htwg.se.blindmaze.model.items.Item
import de.htwg.se.blindmaze.model.Player

//factory pattern

object TileFactory {
  private val playerTiles = scala.collection.mutable.Map[Int, Tile]()
  private val chestTiles = scala.collection.mutable.Map[Item, Tile]()

  def getTile(content: TileContent): Tile = content match {
    case TileContent.Empty => TileCache.EmptyTile
    case TileContent.Wall => TileCache.WallTile
    case TileContent.Victory => TileCache.VictoryTile
    case TileContent.Trap => TileCache.TrapTile
    case TileContent.Player(id) =>
      playerTiles.getOrElseUpdate(id, TileCache.PlayerTile(Player(id)))
    case TileContent.ChestTile(chest) =>
      chestTiles.getOrElseUpdate(chest.item, TileCache.ChestTile(chest.item))
    case _ => Tile(content)
  }
}