package de.htwg.se.blindmaze

import de.htwg.se.blindmaze.core.GameManager

object Main {
  def main(args: Array[String]): Unit = {
    val gameManager = new GameManager()
    gameManager.initGame() // This method initializes and starts the game
  }
}
