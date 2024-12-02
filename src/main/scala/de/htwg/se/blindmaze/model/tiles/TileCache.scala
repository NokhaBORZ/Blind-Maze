package de.htwg.se.blindmaze.model.tiles

import de.htwg.se.blindmaze.model.items.Item
import de.htwg.se.blindmaze.model.Player

//Flyweight Pattern

object TileCache {
  val EmptyTile: Tile = Tile(TileContent.Empty)
  val WallTile: Tile = Tile(TileContent.Wall)
  val VictoryTile: Tile = Tile(TileContent.Victory)
  val TrapTile: Tile = Tile(TileContent.Trap)

  def PlayerTile(player: Player): Tile = Tile(TileContent.Player(player.id))
  def ChestTile(item: Item): Tile = Tile(TileContent.ChestTile(Chest(item)))
}