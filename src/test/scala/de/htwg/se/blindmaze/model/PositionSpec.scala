package de.htwg.se.blindmaze.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PositionSpec extends AnyWordSpec with Matchers {

    "A Position" should {

        "be created with x and y coordinates" in {
            val position = Position(1, 2)
            position.x should be(1)
            position.y should be(2)
        }

        "add another position correctly" in {
            val position1 = Position(1, 2)
            val position2 = Position(3, 4)
            val result = position1 + position2
            result should be(Position(4, 6))
        }

        "check if it is within bounds" in {
            val position = Position(1, 2)
            position.isWithinBounds(3, 3) should be(true)
            position.isWithinBounds(1, 2) should be(false)
        }

        "move in the correct direction" in {
            val position = Position(1, 1)
            position.move(Direction.Up) should be(Position(1, 0))
            position.move(Direction.Down) should be(Position(1, 2))
            position.move(Direction.Left) should be(Position(0, 1))
            position.move(Direction.Right) should be(Position(2, 1))
        }

        "have a correct string representation" in {
            val position = Position(1, 2)
            position.toString should be("(1, 2)")
        }

        "have predefined positions" in {
            Position.Zero should be(Position(0, 0))
            Position.Up should be(Position(0, -1))
            Position.Down should be(Position(0, 1))
            Position.Left should be(Position(-1, 0))
            Position.Right should be(Position(1, 0))
        }
    }
}