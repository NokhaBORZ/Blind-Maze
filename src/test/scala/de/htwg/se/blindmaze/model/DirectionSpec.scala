package de.htwg.se.blindmaze.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DirectionSpec extends AnyWordSpec with Matchers {

    "A Direction" should {

        "have an Up direction" in {
            Direction.Up shouldBe a[Direction]
        }

        "have a Down direction" in {
            Direction.Down shouldBe a[Direction]
        }

        "have a Left direction" in {
            Direction.Left shouldBe a[Direction]
        }

        "have a Right direction" in {
            Direction.Right shouldBe a[Direction]
        }

        "not be equal to another direction" in {
            Direction.Up should not be Direction.Down
            Direction.Left should not be Direction.Right
        }

        "be equal to itself" in {
            Direction.Up should be(Direction.Up)
            Direction.Down should be(Direction.Down)
            Direction.Left should be(Direction.Left)
            Direction.Right should be(Direction.Right)
        }
    }
}