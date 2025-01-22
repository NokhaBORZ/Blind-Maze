package de.htwg.se.blindmaze.model.fileIO

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar
import play.api.libs.json.Json
import de.htwg.se.blindmaze.model.managers.IGameManager
import de.htwg.se.blindmaze.model.fileIO.fileImp.FileIOJson
import java.io.{File, PrintWriter}

class FileIOJsonSpec extends AnyWordSpec with Matchers with MockitoSugar {

    "A FileIOJson" should {

        "save a game manager to a JSON file" in {
            val gameManager = mock[IGameManager]
            val fileIO = new FileIOJson

            val json = Json.obj("test" -> "data")
            when(gameManager.toJson).thenReturn(json)

            fileIO.save(gameManager)

            val source = scala.io.Source.fromFile("maze.json")
            val savedJson = Json.parse(source.mkString)
            source.close()

            savedJson should be(json)
        }
    }
}