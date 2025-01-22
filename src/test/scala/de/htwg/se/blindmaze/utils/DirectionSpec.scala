package de.htwg.se.blindmaze.utils

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.blindmaze.utils.Direction

class DirectionSpec extends AnyWordSpec with Matchers {

    "A Direction" should {

        "have an Up direction" in {
            de.htwg.se.blindmaze.utils.Direction.Up shouldBe a[Direction]
        }

        "have a Down direction" in {
            de.htwg.se.blindmaze.utils.Direction.Down shouldBe a[Direction]
        }

        "have a Left direction" in {
            de.htwg.se.blindmaze.utils.Direction.Left shouldBe a[Direction]
        }

        "have a Right direction" in {
            de.htwg.se.blindmaze.utils.Direction.Right shouldBe a[Direction]
        }

        "not be equal to another direction" in {
            de.htwg.se.blindmaze.utils.Direction.Up should not be de.htwg.se.blindmaze.utils.Direction.Down
            de.htwg.se.blindmaze.utils.Direction.Left should not be de.htwg.se.blindmaze.utils.Direction.Right
        }

        "be equal to itself" in {
            de.htwg.se.blindmaze.utils.Direction.Up should be(de.htwg.se.blindmaze.utils.Direction.Up)
            de.htwg.se.blindmaze.utils.Direction.Down should be(de.htwg.se.blindmaze.utils.Direction.Down)
            de.htwg.se.blindmaze.utils.Direction.Left should be(de.htwg.se.blindmaze.utils.Direction.Left)
            de.htwg.se.blindmaze.utils.Direction.Right should be(de.htwg.se.blindmaze.utils.Direction.Right)
        }
    }
}