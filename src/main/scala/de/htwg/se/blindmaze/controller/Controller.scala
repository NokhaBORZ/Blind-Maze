package de.htwg.se.blindmaze.controller

import de.htwg.se.blindmaze.model.managers.GameManager
import de.htwg.se.blindmaze.utils.Observable
import de.htwg.se.blindmaze.model.commands.Command
import de.htwg.se.blindmaze.model.Direction

class Controller(var gameManager: GameManager) extends Observable {

  def startGame(size: Int = 11): Unit = {
    gameManager = gameManager.startGame
    notifyObservers()
  }

  def movePlayer(direction: Direction): Unit = {
    gameManager = gameManager.moveNext(direction)
    notifyObservers()
  }

  def showGrid: String = gameManager.showGrid

  def executeCommand(command: Command): Unit = {
    command.execute(this)
  }
}