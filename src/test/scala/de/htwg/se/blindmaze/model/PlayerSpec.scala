package de.htwg.se.blindmaze.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerSpec extends AnyWordSpec with Matchers {

    "A Player" should {

        "be created with an empty inventory by default" in {
            val player = Player(1)
            player.inventory should be(empty)
        }

        "add an item to the inventory" in {
            val player = Player(1)
            val item = Map
            val updatedPlayer = player.addItem(item)
            updatedPlayer.inventory should contain(item)
        }

        "remove an item from the inventory" in {
            val item = Map
            val player = Player(1, List(item))
            val updatedPlayer = player.removeItem(item)
            updatedPlayer.inventory should not contain(item)
        }

        "not remove an item that is not in the inventory" in {
            val item = Map
            val player = Player(1)
            val updatedPlayer = player.removeItem(item)
            updatedPlayer.inventory should be(empty)
        }

        "maintain the correct inventory after multiple additions and removals" in {
            val player = Player(1)
            val item1 = Map
            val item2 = Sword
            val item3 = OrbitalStrike

            val updatedPlayer = player
                .addItem(item1)
                .addItem(item2)
                .addItem(item3)
                .removeItem(item2)

            updatedPlayer.inventory should contain(item1)
            updatedPlayer.inventory should contain(item3)
            updatedPlayer.inventory should not contain(item2)
        }
    }
}