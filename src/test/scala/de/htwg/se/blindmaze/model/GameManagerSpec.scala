package de.htwg.se.blindmaze.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.blindmaze.model.Grid
import de.htwg.se.blindmaze.model.GameManager
import de.htwg.se.blindmaze.model.GameState
import de.htwg.se.blindmaze.model.Player

class GameManagerSpec extends AnyWordSpec with Matchers {

    "A GameManager" should {

        "have a default state of NotStarted" in {
            val gameManager = new GameManager()
            gameManager.getState should be(GameState.NotStarted)
        }

        "start the game" in {
            val gameManager = new GameManager()
            val startedGameManager = gameManager.startGame()
            startedGameManager.getState should be(GameState.Running)
        }

        "move the current player" in {
            val gameManager = new GameManager(GameState.Running, new Grid(10).createGrid(List(Player(1), Player(2))), 1)
            val movedGameManager = gameManager.moveNext(Direction.Up)
            movedGameManager.current should be(2)
        }

        "not move the player if the game is not running" in {
            val gameManager = new GameManager(GameState.NotStarted, new Grid(10).createGrid(List(Player(1), Player(2))), 1)
            val movedGameManager = gameManager.moveNext(Direction.Up)
            movedGameManager.current should be(1)
        }

        "show the grid when the game is running" in {
            val gameManager = new GameManager(GameState.Running, new Grid(10).createGrid(List(Player(1), Player(2))), 1)
            gameManager.showGrid() should include("|")
        }

        "not show the grid when the game is not running" in {
            val gameManager = new GameManager(GameState.NotStarted, new Grid(10).createGrid(List(Player(1), Player(2))), 1)
            gameManager.showGrid() should be("Game not running")
        }

        "return the correct current player" in {
            val gameManager = new GameManager(GameState.Running, new Grid(10).createGrid(List(Player(1), Player(2))), 1)
            gameManager.getCurrent should be("Player 1")
            val nextGameManager = gameManager.moveNext(Direction.Up)
            nextGameManager.getCurrent should be("Player 2")
        }
    }
}