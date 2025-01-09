package de.htwg.se.blindmaze.model.managers.managersImp

import de.htwg.se.blindmaze.model._
import de.htwg.se.blindmaze.model.managers.IGameManager
import de.htwg.se.blindmaze.utils.Direction
import de.htwg.se.blindmaze.model.managers.GameState
import de.htwg.se.blindmaze.model.grid.IGrid
import de.htwg.se.blindmaze.model.player.IPlayer

//State Pattern

case class RunningState(
    val grid: IGrid = IGrid(10), 
    val current: Int = 1

) extends IGameManager {
  override def startGame: IGameManager = this

  override def quitGame: IGameManager = {
    println("Quitting the game...")
    NotStartedState()
  }

  override def resetGame: IGameManager = {
    copy (grid.createGrid(List(IPlayer(1), IPlayer(2))), current)
  }

  override def moveNext(direction: Direction): IGameManager = {
    // logic for moving player
    if (!grid.canMove(current, direction)) {
      return this
    }
    val newGrid = grid.movePlayer(current, direction)
    val newCurrent = if (current == 1) 2 else 1
    copy(newGrid, newCurrent)
    
  }
  override def showGrid: String = {
    grid.showGrid()
  }

  override def state: GameState = GameState.Running

  override def invalidCommand: IGameManager = this

  override def changeCurrent: IGameManager = copy(current = if (current == 1) 2 else 1)
}