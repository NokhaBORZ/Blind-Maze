package de.htwg.se.blindmaze.model.grid

import play.api.libs.json.{JsObject, Json, JsString}

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
    def showAllWalls(): IGrid
    def showGrid(): String
    def hasPlayerReachedVictory(playerId: Int): Boolean
    def toXml: scala.xml.Node = {
        <grid size={size.toString}>
            {for {
                x <- 0 until size
                y <- 0 until size
            } yield get(Position(x, y)).toXml}
        </grid>
    }

    def toJson: JsObject = {
        Json.obj(
            "size" -> size,
            "tiles" -> Json.arr((for {
                x <- 0 until size
                y <- 0 until size
            } yield get(Position(x, y)).toJson)*)
        )
    }
}

object IGrid {

    def fromXml(node: scala.xml.Node): IGrid = {
        val size = (node \ "@size").text.toInt
        val grid = new Grid(size)
        (node \ "tile").zipWithIndex.foldLeft(grid) { case (updatedGrid, (tileNode, index)) =>
            val y = index % size
            val x = index / size
            
            val firstElement = tileNode.child.find(_.isInstanceOf[scala.xml.Elem])

            val content = firstElement.map(_.label) match {
                case Some("empty") => TileContent.Empty
                case Some("player") => 
                    TileContent.Player((tileNode \ "player").text.toInt)
                case _ => TileContent.Empty
            }
            println("Position: " + Position(x, y) + " Content: " + content)
            updatedGrid.set(Position(x, y), Tile(content))
        }
    }

    def fromJson(json: play.api.libs.json.JsValue): IGrid = {
        val size = (json \ "size").as[Int]
        val grid = new Grid(size)
        val tiles = (json \ "tiles").as[Seq[JsObject]]
        
        val finalGrid = tiles.zipWithIndex.foldLeft(grid) { case (updatedGrid, (tileJson, index)) =>
            val y = index % size
            val x = index / size

            val content = (tileJson \ "content").toOption match {
                case Some(contentJson) if contentJson.isInstanceOf[JsString] =>
                    contentJson.as[String] match {
                        case "empty" => TileContent.Empty
                        case _ => TileContent.Empty
                    }
                case Some(contentJson: JsObject) =>
                    val playerIdOpt = (contentJson \ "player" \ "id").asOpt[Int]
                    playerIdOpt match {
                        case Some(playerId) => TileContent.Player(playerId)
                        case None => TileContent.Empty
                    }
                case _ =>
                    TileContent.Empty
            }
            updatedGrid.set(Position(x, y), Tile(content))
        }
        finalGrid
    }
}