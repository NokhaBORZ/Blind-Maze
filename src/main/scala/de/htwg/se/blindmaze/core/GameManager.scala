package de.htwg.se.blindmaze.core

import scala.io.AnsiColor.{GREEN, RED, RESET}
import de.htwg.se.blindmaze.utils.{InputHandler, DisplayRenderer, Input}
import de.htwg.se.blindmaze.model.Player
import de.htwg.se.blindmaze.math.Position

class GameManager() {

  var gameState: GameState = GameState.Running
  val initialPlayer = Player("P", Position.Zero)
  var grid = Grid.DefaultGrid(initialPlayer, 10, 10)

  def run(): Boolean = {
    println(s"${GREEN}The Game Blind Maze!${RESET}")
    println("Find your way through the maze to the exit.")
    println("w,a,s,d for movement.")
    println("Use 'q' to quit.")
    println("")
    update(initialPlayer)
    gameState == GameState.GameOver
  }

  def update(currentPlayer: Player): Unit = {
    println(DisplayRenderer.render(grid))

    val input = InputHandler.parseInput(scala.io.StdIn.readLine())
    val updatedPlayer = handleInput(input, currentPlayer)

    if (gameState == GameState.Running) {
      update(updatedPlayer)
    }
  }

  def handleInput(input: Option[Input], currentPlayer: Player): Player = {
    input match {
      case Some(Input.Move(direction)) =>
        val oldPosition = currentPlayer.position
        val newPosition = currentPlayer.position.move(direction)
        if (newPosition.isWithinBounds(grid.width, grid.height)) {
          val updatedPlayer = currentPlayer.copy(position =
            newPosition
          ) // Create a new player instance
          grid = grid.updatePlayerPosition(
            updatedPlayer,
            oldPosition
          ) // Update the grid with the new player position
          updatedPlayer // Return the updated player
        } else {
          println(s"${RED}Cannot move out of bounds${RESET}")
          currentPlayer // Return the current player without change
        }

      case Some(Input.Quit) =>
        gameState = GameState.GameOver
        println(s"${RED}Game over!${RESET}")
        currentPlayer // Return the current player as quitting stops the game

      case None =>
        println("Invalid input!")
        currentPlayer // Return the current player without change
    }
  }
}
