package de.htwg.se.blindmaze.controller
package de.htwg.se.blindmaze.controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.blindmaze.model.{GameManager, Direction}
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar

class ControllerSpec extends AnyWordSpec with Matchers with MockitoSugar {

    "A Controller" should {

        "start a new game" in {
            val gameManager = mock[GameManager]
            val controller = new Controller(gameManager)
            when(gameManager.startGame()).thenReturn(gameManager)

            controller.startGame()

            verify(gameManager).startGame()
            controller.gameManager should be(gameManager)
        }

        "move the player" in {
            val gameManager = mock[GameManager]
            val controller = new Controller(gameManager)
            val direction = Direction.NORTH
            when(gameManager.moveNext(direction)).thenReturn(gameManager)

            controller.movePlayer(direction)

            verify(gameManager).moveNext(direction)
            controller.gameManager should be(gameManager)
        }

        "show the grid" in {
            val gameManager = mock[GameManager]
            val controller = new Controller(gameManager)
            val gridString = "Grid"
            when(gameManager.showGrid()).thenReturn(gridString)

            val result = controller.showGrid

            result should be(gridString)
        }
    }
}
