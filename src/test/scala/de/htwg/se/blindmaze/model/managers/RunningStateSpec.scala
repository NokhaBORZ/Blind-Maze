package de.htwg.se.blindmaze.model.managers

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar
import org.mockito.ArgumentMatchers._
import de.htwg.se.blindmaze.model.grid.IGrid
import de.htwg.se.blindmaze.model.player.IPlayer
import de.htwg.se.blindmaze.model.managers.managersImp.{RunningState, FinishedState, NotStartedState}
import de.htwg.se.blindmaze.utils.Direction
import de.htwg.se.blindmaze.utils.Position

import de.htwg.se.blindmaze.model.tiles.{Tile, TileContent}

class RunningStateSpec extends AnyWordSpec with Matchers with MockitoSugar {

    "A RunningState" should {

        "return the same state when startGame is called" in {
            val grid = mock[IGrid]
            val runningState = RunningState(grid)

            runningState.startGame should be(runningState)
        }

        "return NotStartedState when quitGame is called" in {
            val grid = mock[IGrid]
            val runningState = RunningState(grid)

            runningState.quitGame shouldBe a[NotStartedState]
        }

        "return the grid representation when showGrid is called" in {
            val grid = mock[IGrid]
            when(grid.showGrid()).thenReturn("Grid representation")
            val runningState = RunningState(grid, 1)

            runningState.showGrid should be("Grid representation")
        }

        "return Running state when state is called" in {
            val grid = mock[IGrid]
            val runningState = RunningState(grid, 1)

            runningState.state should be(GameState.Running)
        }

        "return the same state when invalidCommand is called" in {
            val grid = mock[IGrid]
            val runningState = RunningState(grid, 1)

            runningState.invalidCommand should be(runningState)
        }

        "return a new RunningState with switched player when changeCurrent is called" in {
            val grid = mock[IGrid]
            val runningState = RunningState(grid, 1)

            val newState = runningState.changeCurrent
            newState shouldBe a[RunningState]
            newState.current should be(2)
        }
    }
}