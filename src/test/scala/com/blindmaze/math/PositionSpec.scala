package com.blindmaze.math

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class PositionSpec extends AnyWordSpec with Matchers {

    "A Position" when {
        "moving up" should {
            "move correctly" in {
                val startPos = Position(2, 2)
                val newPos = startPos.move(Direction.Up)
                newPos shouldEqual Position(2, 1)
            }
        }

        "moving down" should {
            "move correctly" in {
                val startPos = Position(2, 2)
                val newPos = startPos.move(Direction.Down)
                newPos shouldEqual Position(2, 3)
            }
        }

        "moving left" should {
            "move correctly" in {
                val startPos = Position(2, 2)
                val newPos = startPos.move(Direction.Left)
                newPos shouldEqual Position(1, 2)
            }
        }

        "moving right" should {
            "move correctly" in {
                val startPos = Position(2, 2)
                val newPos = startPos.move(Direction.Right)
                newPos shouldEqual Position(3, 2)
            }
        }

        "moving in an invalid direction" should {
            "not change position" in {
                val startPos = Position(2, 2)
                val newPos = startPos.move(null)
                newPos shouldEqual startPos
            }
        }
    }
}