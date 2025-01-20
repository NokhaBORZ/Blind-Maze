package de.htwg.se.blindmaze.model.fileIO.fileImp

import play.api.libs.json.Json

import de.htwg.se.blindmaze.model.fileIO.IFileIO
import de.htwg.se.blindmaze.model.managers.IGameManager

class FileIOJson extends IFileIO {
    override def load: IGameManager = {
        val source = scala.io.Source.fromFile("maze.json")
        val json = Json.parse(source.mkString)
        source.close()
        IGameManager.fromJson(json)
    }

    override def save(gameManager: IGameManager): Unit = {
        val pw = new java.io.PrintWriter("maze.json")
        pw.write(Json.prettyPrint(gameManager.toJson))
        pw.close()
    }
}