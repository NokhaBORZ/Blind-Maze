package de.htwg.se.blindmaze.model.managers

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import play.api.libs.json.Json
import scala.xml.PrettyPrinter
import de.htwg.se.blindmaze.model.grid.gridImp.Grid
import de.htwg.se.blindmaze.model.player.playerImp.Player
import de.htwg.se.blindmaze.model.managers.IGameManager
import de.htwg.se.blindmaze.model.managers.managersImp.{RunningState}
import de.htwg.se.blindmaze.utils.Direction

class IGameManagerSpec extends AnyWordSpec with Matchers {

    "A GameManager" should {
        val grid = new Grid(11)
        val player1 = Player(1)
        val player2 = Player(2)
        val gameManager = RunningState(grid.createGrid(List(player1, player2)), 1)

        "start the game" in {
            val newGameManager = gameManager.startGame
            newGameManager.state should be(GameState.Running)
        }

        "quit the game" in {
            val newGameManager = gameManager.quitGame
            newGameManager.state should be(GameState.NotStarted)
        }

        "move the player" in {
            val newGameManager = gameManager.moveNext(Direction.Up, 1)
            newGameManager.current should be(1)
        }

        "return invalid command" in {
            val newGameManager = gameManager.invalidCommand
            newGameManager should be(gameManager)
        }

        "show the grid" in {
            val gridString = gameManager.showGrid
            gridString should not be empty
        }

        "reset the game" in {
            val newGameManager = gameManager.resetGame
            newGameManager.state should be(GameState.Running)
        }

        "change the current player" in {
            val newGameManager = gameManager.changeCurrent
            newGameManager.current should be(2)
        }

        "serialize to XML" in {
            val xml = gameManager.toXml
            val prettyPrinter = new PrettyPrinter(80, 2)
            val xmlString = prettyPrinter.format(xml)
            xmlString should include("<game>")
            xmlString should include("<current>1</current>")
        }

        "deserialize from XML" in {
            val xml = gameManager.toXml
            val newGameManager = IGameManager.fromXml(xml)
            newGameManager.current should be(1)
            newGameManager.state should be(GameState.Running)
        }

        "serialize to JSON" in {
            val json = gameManager.toJson
            val jsonString = Json.prettyPrint(json)
            jsonString should include("\"current\" : 1")
        }

        "deserialize from JSON" in {
            val json = gameManager.toJson
            val newGameManager = IGameManager.fromJson(json)
            newGameManager.current should be(1)
            newGameManager.state should be(GameState.Running)
        }
    }
}