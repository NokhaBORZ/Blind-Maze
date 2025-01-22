package de.htwg.se.blindmaze.model.managers

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar
import de.htwg.se.blindmaze.model.grid.IGrid
import de.htwg.se.blindmaze.model.player.IPlayer
import de.htwg.se.blindmaze.model.managers.managersImp.{FinishedState, NotStartedState, RunningState}
import de.htwg.se.blindmaze.utils.Direction

class FinishedStateSpec extends AnyWordSpec with Matchers with MockitoSugar {

    "A FinishedState" should {

        "return the same state when startGame is called" in {
            val grid = mock[IGrid]
            val finishedState = FinishedState(grid, 1)

            finishedState.startGame should be(finishedState)
        }

        "return NotStartedState when quitGame is called" in {
            val grid = mock[IGrid]
            val finishedState = FinishedState(grid, 1)

            finishedState.quitGame shouldBe a[NotStartedState]
        }

        "return RunningState when resetGame is called" in {
            val grid = mock[IGrid]
            val player1 = mock[IPlayer]
            val player2 = mock[IPlayer]
            val finishedState = FinishedState(grid, 1)

            val newGrid = mock[IGrid]
            when(grid.createGrid(List(player1, player2))).thenReturn(newGrid)

            finishedState.resetGame shouldBe a[RunningState]
        }

        "return the same state when moveNext is called" in {
            val grid = mock[IGrid]
            val finishedState = FinishedState(grid, 1)

            finishedState.moveNext(Direction.Up, 1) should be(finishedState)
        }

        "return the grid representation when showGrid is called" in {
            val grid = mock[IGrid]
            val finishedState = FinishedState(grid, 1)

            when(grid.showGrid()).thenReturn("Grid representation")

            finishedState.showGrid should be("Grid representation")
        }

        "return Finished state when state is called" in {
            val grid = mock[IGrid]
            val finishedState = FinishedState(grid, 1)

            finishedState.state should be(GameState.Finished)
        }

        "return the same state when invalidCommand is called" in {
            val grid = mock[IGrid]
            val finishedState = FinishedState(grid, 1)

            finishedState.invalidCommand should be(finishedState)
        }

        "return the winner message when getWinnerMessage is called" in {
            val grid = mock[IGrid]
            val finishedState = FinishedState(grid, 1)

            finishedState.getWinnerMessage should be("Player 1 has won the game!")
        }
    }
}