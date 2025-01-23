package de.htwg.se.blindmaze.utils

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class DirectionSpec extends AnyWordSpec with Matchers {
    "A Direction" should {
        "have an Up direction" in {
            Direction.Up.toString should be("Up")
        }
        "have a Down direction" in {
            Direction.Down.toString should be("Down")
        }
        "have a Left direction" in {
            Direction.Left.toString should be("Left")
        }
        "have a Right direction" in {
            Direction.Right.toString should be("Right")
        }
        "have correct opposites" in {
            Direction.opposite(Direction.Up) should be(Direction.Down)
            Direction.opposite(Direction.Down) should be(Direction.Up)
            Direction.opposite(Direction.Left) should be(Direction.Right)
            Direction.opposite(Direction.Right) should be(Direction.Left)
        }
        "not be equal to a different direction" in {
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