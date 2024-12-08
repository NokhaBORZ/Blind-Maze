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
  def moveNext(direction: Direction): GameManager
  def showGrid: String

  def state : GameState
}

object GameManager {
  def apply(): GameManager = NotStartedState()
}

