package de.htwg.se.blindmaze.model.managers

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.blindmaze.model.{Grid}
import de.htwg.se.blindmaze.utils.Direction

class GameManagerSpec extends AnyWordSpec with Matchers {

    "A GameManager" should {

        "start the game" in {
            val gameManager = GameManager()
            val startedGameManager = gameManager.startGame
            startedGameManager.state should be(GameState.Running)
        }

        "move to the next position" in {
            val gameManager = GameManager().startGame
            val movedGameManager = gameManager.moveNext(Direction.Down)
            movedGameManager.current should not be gameManager.current
        }

        "show the grid" in {
            val gameManager = GameManager().startGame
            val gridString = gameManager.showGrid
            gridString should not be empty
        }

        "have a NotStarted state initially" in {
            val gameManager = GameManager()
            gameManager.state should be(GameState.NotStarted)
        }
    }
}