package de.htwg.se.blindmaze.model.managers

import de.htwg.se.blindmaze.model.Direction
import de.htwg.se.blindmaze.model.Grid

//State Pattern

enum GameState:
  case NotStarted, Running, Finished

trait GameManager {
  val grid: Grid
  val current: Int
  def startGame: GameManager
  def quitGame: GameManager
  def moveNext(direction: Direction): GameManager
  def invalidCommand: GameManager
  def showGrid: String
  def resetGame: GameManager
  def changeCurrent: GameManager = this

  def state : GameState
}

object GameManager {
  def apply(): GameManager = NotStartedState()
}

