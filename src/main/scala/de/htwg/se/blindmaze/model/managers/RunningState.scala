package de.htwg.se.blindmaze.model.managers

import de.htwg.se.blindmaze.model._
import de.htwg.se.blindmaze.model.managers.GameManager

//State Pattern

case class RunningState(
    val grid: Grid = new Grid(10), 
    val current: Int = 1

) extends GameManager {
  override def startGame: GameManager = this

  override def quitGame: GameManager = {
    println("Quitting the game...")
    NotStartedState()
  }

  override def resetGame: GameManager = {
    copy (grid.createGrid(List(Player(1), Player(2))), current)
  }

  override def moveNext(direction: Direction): GameManager = {
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

  override def invalidCommand: GameManager = this

  override def changeCurrent: GameManager = copy(current = if (current == 1) 2 else 1)
}