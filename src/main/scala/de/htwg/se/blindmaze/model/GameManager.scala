package de.htwg.se.blindmaze.model

import de.htwg.se.blindmaze.model.{GameState, Grid}
import de.htwg.se.blindmaze.model.generator.WallGenerator

class GameManager(
  val state: GameState = GameState.NotStarted,
  val grid: Grid = new Grid(10), 
  val current: Int = 1
) {

  private val wallGenerator = new WallGenerator()

  def getState: GameState = state

  def startGame(): GameManager = {
    new GameManager(GameState.Running, wallGenerator.setupGrid(new Grid(10)), current)
  }

  def moveNext(direction: Direction): GameManager = {
    if(state == GameState.Running) {
      val (newGrid, collision) = grid.movePlayer(current, direction)
      if (collision.isDefined) {
        new GameManager(state, newGrid, current) // Current player waits for one round
      } else {
        new GameManager(state, newGrid, if(current == 1) 2 else 1)
      }
    } else {
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
    if(current == 1) "Player 1" else "Player 2"
  }
}
