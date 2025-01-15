package de.htwg.se.blindmaze.model.grid

import de.htwg.se.blindmaze.model.tiles.{Tile, TileContent}
import de.htwg.se.blindmaze.utils.{Direction, Position}
import de.htwg.se.blindmaze.model.player.IPlayer
import de.htwg.se.blindmaze.model.grid.gridImp.Grid

trait IGrid {
    def createGrid(playerList: List[IPlayer]): IGrid
    def set(position: Position, tile: Tile): IGrid
    def get(position: Position): Tile
    def size: Int
    def movePlayer(playerId: Int, direction: Direction): IGrid
    def canMove(playerId: Int, direction: Direction): Boolean
    def getPlayer(player: IPlayer): Option[Position]
    def inBounds(position: Position): Boolean
    def showGrid(): String
}