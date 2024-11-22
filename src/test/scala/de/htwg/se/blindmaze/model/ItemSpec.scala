package de.htwg.se.blindmaze.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ItemSpec extends AnyWordSpec with Matchers {

    "A Map item" should {
        "reveal tiles in the direction of the exit for 3 moves" in {
            val player = Player(1)
            val gameManager = new GameManager(GameState.Running, new Grid(10).createGrid(List(player)), 1)
            val updatedGameManager = Map.use(player, gameManager)
            // Add assertions to verify the map usage logic
            updatedGameManager should be(gameManager) // Placeholder assertion
        }
    }

    "An OrbitalStrike item" should {
        "stun all other players for 2-3 rounds" in {
            val player1 = Player(1)
            val player2 = Player(2)
            val gameManager = new GameManager(GameState.Running, new Grid(10).createGrid(List(player1, player2)), 1)
            val updatedGameManager = OrbitalStrike.use(player1, gameManager)
            // Add assertions to verify the orbital strike logic
            updatedGameManager should be(gameManager) // Placeholder assertion
        }
    }

    "A Sword item" should {
        "stun nearby players for 1 round" in {
            val player1 = Player(1)
            val player2 = Player(2)
            val gameManager = new GameManager(GameState.Running, new Grid(10).createGrid(List(player1, player2)), 1)
            val updatedGameManager = Sword.use(player1, gameManager)
            // Add assertions to verify the sword usage logic
            updatedGameManager should be(gameManager) // Placeholder assertion
        }
    }
}