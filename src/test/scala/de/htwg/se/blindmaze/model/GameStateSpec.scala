package de.htwg.se.blindmaze.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameStateSpec extends AnyWordSpec with Matchers {

    "A GameState" should {

        "be Running when the game is in progress" in {
            GameState.Running shouldBe a[GameState]
        }

        "be NotStarted when the game has not started yet" in {
            GameState.NotStarted shouldBe a[GameState]
        }

        "be GameOver when the game has ended" in {
            GameState.GameOver shouldBe a[GameState]
        }

        "be Paused when the game is paused" in {
            GameState.Paused shouldBe a[GameState]
        }

        "be Victory when the game is won" in {
            GameState.Victory shouldBe a[GameState]
        }

        "not be equal to another state" in {
            GameState.Running should not be GameState.NotStarted
            GameState.GameOver should not be GameState.Paused
            GameState.Victory should not be GameState.Running
        }

        "be equal to itself" in {
            GameState.Running should be(GameState.Running)
            GameState.NotStarted should be(GameState.NotStarted)
            GameState.GameOver should be(GameState.GameOver)
            GameState.Paused should be(GameState.Paused)
            GameState.Victory should be(GameState.Victory)
        }
    }
}