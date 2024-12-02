package de.htwg.se.blindmaze.model.managers

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.blindmaze.model._

class RunningStateSpec extends AnyWordSpec with Matchers {

    "A RunningState" should {

        "have a default grid size of 10" in {
            val state = RunningState()
            state.grid.size should be(10)
        }

        "have a default current player of 1" in {
            val state = RunningState()
            state.current should be(1)
        }

        "remain in RunningState when startGame is called" in {
            val state = RunningState()
            val newState = state.startGame
            newState shouldBe state
        }

        "move to the next position if possible" in {
            val state = RunningState()
            val newState = state.moveNext(Direction.Up)
            if (state.grid.canMove(state.current, Direction.Up)) {
                newState should not be state
            } else {
                newState should be(state)
            }
        }

        "switch to the next player after a move" in {
            val state = RunningState()
            val newState = state.moveNext(Direction.Up)
            if (state.grid.canMove(state.current, Direction.Up)) {
                newState.current should not be state.current
            } else {
                newState.current should be(state.current)
            }
        }

        "show the grid" in {
            val state = RunningState()
            val gridString = state.showGrid
            gridString should not be empty
        }

        "have a Running state" in {
            val state = RunningState()
            state.state should be(GameState.Running)
        }
    }
}