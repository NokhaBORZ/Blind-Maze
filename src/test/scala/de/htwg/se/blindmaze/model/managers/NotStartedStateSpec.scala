package de.htwg.se.blindmaze.model.managers

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar
import de.htwg.se.blindmaze.model.grid.IGrid
import de.htwg.se.blindmaze.model.managers.managersImp.{NotStartedState, RunningState}
import de.htwg.se.blindmaze.utils.Generator
import de.htwg.se.blindmaze.utils.Direction

class NotStartedStateSpec extends AnyWordSpec with Matchers with MockitoSugar {

    "A NotStartedState" should {

        "return RunningState when startGame is called" in {
            val grid = mock[IGrid]
            val notStartedState = NotStartedState(grid)

            notStartedState.startGame shouldBe a[RunningState]
        }

        "return the same state when quitGame is called" in {
            val grid = mock[IGrid]
            val notStartedState = NotStartedState(grid)

            notStartedState.quitGame should be(notStartedState)
        }

        "return the same state when resetGame is called" in {
            val grid = mock[IGrid]
            val notStartedState = NotStartedState(grid)

            notStartedState.resetGame should be(notStartedState)
        }

        "return the same state when moveNext is called" in {
            val grid = mock[IGrid]
            val notStartedState = NotStartedState(grid)

            notStartedState.moveNext(Direction.Up, 1) should be(notStartedState)
        }

        "return 'Game not running' when showGrid is called" in {
            val grid = mock[IGrid]
            val notStartedState = NotStartedState(grid)

            notStartedState.showGrid should be("Game not running")
        }

        "return NotStarted state when state is called" in {
            val grid = mock[IGrid]
            val notStartedState = NotStartedState(grid)

            notStartedState.state should be(GameState.NotStarted)
        }

        "return the same state when invalidCommand is called" in {
            val grid = mock[IGrid]
            val notStartedState = NotStartedState(grid)

            notStartedState.invalidCommand should be(notStartedState)
        }
    }
}