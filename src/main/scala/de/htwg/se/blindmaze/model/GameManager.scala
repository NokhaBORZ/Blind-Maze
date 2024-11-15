package de.htwg.se.blindmaze.model

import de.htwg.se.blindmaze.model.{GameState, Grid}

class GameManager(
  val state: GameState = GameState.NotStarted,
  val grid: Grid = new Grid(10), 
  val current: Int = 1
) {

  def getState: GameState = state

  def startGame(): GameManager = {
    new GameManager(GameState.Running, grid.createGrid(List(Player(1), Player(2))), current)
  }

  def moveNext(direction: Direction): GameManager = {
    state match {
      case GameState.Running =>
        val newGrid = grid.movePlayer(current, direction)
        if (newGrid == grid) {
          println("Not valid move")
          this
        } else {
          new GameManager(state, newGrid, if (current == 1) 2 else 1)
        }

      case _ =>
        this
    }
  }

  def showGrid(): String = {
    if(state == GameState.Running) {
      grid.showGrid()
    } else {
      "Game not running"
    }
  }

  def getCurrent: String = {
    if(current == 0) "Player 1" else "Player 2"
  }
}
