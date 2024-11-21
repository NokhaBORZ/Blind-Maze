// Item.scala
package de.htwg.se.blindmaze.model

sealed trait Item {
  def use(player: Player, gameManager: GameManager): GameManager
}

case object Map extends Item {
  def use(player: Player, gameManager: GameManager): GameManager = {
    // Implement map usage logic
    // For example, reveal tiles in the direction of the exit for 3 moves
    gameManager // Return updated GameManager
  }
}

case object OrbitalStrike extends Item {
  def use(player: Player, gameManager: GameManager): GameManager = {
    // Implement orbital strike logic
    // For example, stun all other players for 2-3 rounds
    gameManager // Return updated GameManager
  }
}

case object Sword extends Item {
  def use(player: Player, gameManager: GameManager): GameManager = {
    // Implement sword usage logic
    // For example, check for nearby players and stun them for 1 round
    gameManager // Return updated GameManager
  }
}