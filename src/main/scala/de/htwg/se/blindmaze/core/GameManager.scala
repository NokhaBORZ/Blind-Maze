package de.htwg.se.blindmaze.core

import de.htwg.se.blindmaze.model.{Grid, Player}
import de.htwg.se.blindmaze.math.Position
import de.htwg.se.blindmaze.core.GameState
import de.htwg.se.blindmaze.utils.Display
import de.htwg.se.blindmaze.math.Direction

class GameManager() {

  private val grid: Grid = Grid.defaultGrid() // Initialize default grid
  private var gameState: GameState = GameState.Running
  private var players: List[Player] = List()  // Initialize empty player list
  private var moveHistory: Map[String, List[Position]] = Map()

  /** Initializes the game by setting up players and starting the game loop. */
  def initGame(): Boolean = {
    println(s"\n${GREEN}Welcome to Blind-Maze${RESET}\n")
    val numPlayersOpt = try {
      Some(scala.io.StdIn.readLine("Enter number of players (2-4): ").toInt)
    } catch {
      case _: NumberFormatException => None
    }

    numPlayersOpt match {
      case Some(numPlayers) if numPlayers >= 2 && numPlayers <= 4 =>
        players = (1 to numPlayers).map(i => Player(s"Player$i", Position(0, i))).toList
        moveHistory = players.map(player => player.id -> List()).toMap
        runGame()
        true
      case _ =>
        println("Invalid number of players. Please enter a number between 2 and 4.")
        false
    }
  }

  private def runGame(): Unit = {
    import scala.util.control.Breaks._
    breakable {
      while (gameState == GameState.Running) {
        players.foreach { player =>
          DisplayRenderer.render(grid)
          if (handleInput(player) && checkGameState(player)) break() // Exit if game ends
        }
      }
    }
  }

  private def handleInput(player: Player): Boolean = {
    val input = InputHandler.handleInput(
      scala.io.StdIn.readLine(s"Enter move for ${player.id}: ")
    )
    input match {
      case Input.Move(direction) =>
        grid.movePlayer(player, direction) match {
          case Some(newPosition) =>
            moveHistory = moveHistory.updated(
              player.id,
              moveHistory(player.id) :+ newPosition
            )
            if (checkForRelic(newPosition, player)) checkGameState(player)
            true
          case None =>
            println("Move out of bounds.")
            false
        }
      case Input.Quit =>
        gameState = GameState.GameOver
        false
    }
  }

  private def checkForRelic(position: Position, player: Player): Boolean = {
    grid.getTile(position) match {
      case Some(tile) =>
        tile.content match {
          case Some(relic: Relic) =>
            relic.use(player)
            tile.content = None
            true
          case _ => false
        }
      case None => false
    }
  }

  private def checkGameState(player: Player): Boolean = {
    if (allRelicsCollected()) {
      gameState = GameState.Victory
      println(s"Congratulations, ${player.id}! You've collected all relics and won!")
      true
    } else if (moveHistory(player.id).contains(player.position)) {
      gameState = GameState.GameOver
      println(s"${player.id} lost! Caught by an echo!")
      true
    } else false
  }

  private def allRelicsCollected(): Boolean = {
    !grid.tiles.exists(_.content.exists(_.isInstanceOf[Relic]))
  }
}