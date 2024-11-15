package de.htwg.se.blindmaze.controller

import de.htwg.se.blindmaze.model.{GameManager, Player}
import de.htwg.se.blindmaze.model.Direction
import de.htwg.se.blindmaze.utils.Observable


class Controller(var gameManager: GameManager) extends Observable {

  def startGame(size: Int = 11): Unit = {
    gameManager = gameManager.startGame()
    notifyObservers()
  }

  def movePlayer(direction: Direction): Unit = {
    gameManager = gameManager.moveNext(direction)
    notifyObservers()
  }

  def showGrid: String =
    gameManager.showGrid()
    
}