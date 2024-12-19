package de.htwg.se.blindmaze.model.managers

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.blindmaze.model._
import de.htwg.se.blindmaze.utils.Direction

class NotStartedStateSpec extends AnyWordSpec with Matchers {

    "A NotStartedState" should {

        "have a default grid size of 10" in {
            val state = NotStartedState()
            state.grid.size should be(10)
        }

        "have a default current player of 1" in {
            val state = NotStartedState()
            state.current should be(1)
        }

        "return RunningState when startGame is called" in {
            val state = NotStartedState()
            val newState = state.startGame
            newState shouldBe a[RunningState]
        }

        "not change state when moveNext is called" in {
            val state = NotStartedState()
            val newState = state.moveNext(Direction.Up)
            newState should be(state)
        }

        "show 'Game not running' when showGrid is called" in {
            val state = NotStartedState()
            state.showGrid should be("Game not running")
        }

        "have a state of NotStarted" in {
            val state = NotStartedState()
            state.state should be(GameState.NotStarted)
        }
    }
}