package de.htwg.se.blindmaze.model.managers.managersImp

import de.htwg.se.blindmaze.model._
import de.htwg.se.blindmaze.model.managers.IGameManager
import de.htwg.se.blindmaze.utils.Direction
import de.htwg.se.blindmaze.model.managers.GameState
import de.htwg.se.blindmaze.model.grid.IGrid
import de.htwg.se.blindmaze.model.player.IPlayer
//State Pattern

case class NotStartedState(
    val grid: IGrid = IGrid(10), 
    val current: Int = 1
  ) 
  extends IGameManager {
  override def startGame: IGameManager = {
    RunningState (grid.createGrid(List(IPlayer(1), IPlayer(2))), current)
  }
  override def quitGame: IGameManager = this
   
  override def resetGame: IGameManager = this
  
  override def moveNext(direction: Direction): IGameManager = this
  override def showGrid: String = "Game not running"
  override def state: GameState = GameState.NotStarted

  override def invalidCommand: IGameManager = this
}