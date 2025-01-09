package de.htwg.se.blindmaze.model.tiles

import de.htwg.se.blindmaze.model.item.IItem
import de.htwg.se.blindmaze.model.player.IPlayer

//Flyweight Pattern

object TileCache {
  val EmptyTile: Tile = Tile(TileContent.Empty)
  val WallTile: Tile = Tile(TileContent.Wall)
  val VictoryTile: Tile = Tile(TileContent.Victory)
  val TrapTile: Tile = Tile(TileContent.Trap)

  def PlayerTile(player: IPlayer): Tile = Tile(TileContent.Player(player.id))
  def ChestTile(item: IItem): Tile = Tile(TileContent.ChestTile(Chest(item)))
}