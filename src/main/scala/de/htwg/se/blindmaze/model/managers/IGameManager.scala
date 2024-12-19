package de.htwg.se.blindmaze.model.managers

import de.htwg.se.blindmaze.utils.Direction
import de.htwg.se.blindmaze.model.grid.IGrid
import de.htwg.se.blindmaze.model.managers.managersImp.NotStartedState


//State Pattern

enum GameState:
  case NotStarted, Running, Finished

trait  IGameManager {
  val grid: IGrid
  val current: Int
  def startGame:  IGameManager
  def quitGame: IGameManager
  def moveNext(direction: Direction): IGameManager
  def invalidCommand: IGameManager
  def showGrid: String
  def resetGame: IGameManager
  def changeCurrent: IGameManager = this

  def state : GameState
}

object IGameManager {
  def apply(): IGameManager = NotStartedState()
}

