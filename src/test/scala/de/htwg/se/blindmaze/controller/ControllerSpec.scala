package de.htwg.se.blindmaze.controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.blindmaze.model.{GameManager, Direction, Grid, GameState}
import de.htwg.se.blindmaze.model.Player
import de.htwg.se.blindmaze.model.Position
import de.htwg.se.blindmaze.utils.Observer

class ControllerSpec extends AnyWordSpec with Matchers {

    "A Controller" should {

        "start a new game" in {
            val gameManager = new GameManager()
            val controller = new Controller(gameManager)
            controller.startGame()
            controller.gameManager.getState should be(GameState.Running)
        }

        "move a player" in {
            val gameManager = new GameManager(GameState.Running, new Grid(10), 1)
            val controller = new Controller(gameManager)
            controller.movePlayer(Direction.Up)
            // Assuming the player starts at (0, 0) and moves up to (0, -1)
            controller.gameManager.grid.get(controller.gameManager.grid.getPlayer(Player(1))) should not be (Position(0, 0))
        }

        "show the grid" in {
            val gameManager = new GameManager(GameState.Running, new Grid(10), 1)
            val controller = new Controller(gameManager)
            controller.showGrid should include ("|")
        }

        "notify observers when game starts" in {
            val gameManager = new GameManager()
            val controller = new Controller(gameManager)
            var notified = false
            controller.add(new Observer {
                override def update(): Unit = notified = true
            })
            controller.startGame()
            notified should be(true)
        }

        "notify observers when player moves" in {
            val gameManager = new GameManager(GameState.Running, new Grid(10), 1)
            val controller = new Controller(gameManager)
            var notified = false
            controller.add(new Observer {
                override def update(): Unit = notified = true
            })
            controller.movePlayer(Direction.Up)
            notified should be(true)
        }
    }
}