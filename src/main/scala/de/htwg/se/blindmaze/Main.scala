package de.htwg.se.blindmaze.core

import de.htwg.se.blindmaze.core.GameManager

object Main {
  def main(args: Array[String]): Unit = {
    val gameManager = new GameManager()
    gameManager.initGame() // Initializes and starts the game
  }
}
