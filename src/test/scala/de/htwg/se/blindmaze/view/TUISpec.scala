package de.htwg.se.blindmaze.view

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.blindmaze.controller.Controller
import de.htwg.se.blindmaze.model.{Direction, GameManager, GameState, Grid, Player}

class TUISpec extends AnyWordSpec with Matchers {

    "A TUI" should {

        "update the view when notified" in {
            val gameManager = new GameManager(GameState.Running, new Grid(10), 1)
            val controller = new Controller(gameManager)
            val tui = new TUI(controller)
            tui.update()
            // Add assertions to verify the update logic
        }

        "process input 'q' to quit the game" in {
            val gameManager = new GameManager()
            val controller = new Controller(gameManager)
            val tui = new TUI(controller)
            tui.processInputLine("q")
            // Add assertions to verify the quit logic
        }

        "process input 'n' to start the game" in {
            val gameManager = new GameManager()
            val controller = new Controller(gameManager)
            val tui = new TUI(controller)
            tui.processInputLine("n")
            controller.gameManager.getState should be(GameState.Running)
        }

        "process input 'w' to move the player up" in {
            val gameManager = new GameManager(GameState.Running, new Grid(10).createGrid(List(Player(1))), 1)
            val controller = new Controller(gameManager)
            val tui = new TUI(controller)
            tui.processInputLine("w")
            // Add assertions to verify the player movement logic
        }

        "process input 's' to move the player down" in {
            val gameManager = new GameManager(GameState.Running, new Grid(10).createGrid(List(Player(1))), 1)
            val controller = new Controller(gameManager)
            val tui = new TUI(controller)
            tui.processInputLine("s")
            // Add assertions to verify the player movement logic
        }

        "process input 'a' to move the player left" in {
            val gameManager = new GameManager(GameState.Running, new Grid(10).createGrid(List(Player(1))), 1)
            val controller = new Controller(gameManager)
            val tui = new TUI(controller)
            tui.processInputLine("a")
            // Add assertions to verify the player movement logic
        }

        "process input 'd' to move the player right" in {
            val gameManager = new GameManager(GameState.Running, new Grid(10).createGrid(List(Player(1))), 1)
            val controller = new Controller(gameManager)
            val tui = new TUI(controller)
            tui.processInputLine("d")
            // Add assertions to verify the player movement logic
        }

        "handle invalid input" in {
            val gameManager = new GameManager()
            val controller = new Controller(gameManager)
            val tui = new TUI(controller)
            tui.processInputLine("x")
            // Add assertions to verify the invalid input handling
        }
    }
}