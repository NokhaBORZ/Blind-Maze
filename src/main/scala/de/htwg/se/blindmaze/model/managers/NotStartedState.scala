package de.htwg.se.blindmaze.model.managers

import de.htwg.se.blindmaze.model._

//State Pattern

case class NotStartedState(
    val grid: Grid = new Grid(10), 
    val current: Int = 1
  ) 
  extends GameManager {
  override def startGame: GameManager = {
    RunningState (grid.createGrid(List(Player(1), Player(2))), current)
  }
  override def quitGame: GameManager = this
   
  override def resetGame: GameManager = this
  
  override def moveNext(direction: Direction): GameManager = this
  override def showGrid: String = "Game not running"
  override def state: GameState = GameState.NotStarted

  override def invalidCommand: GameManager = this
}