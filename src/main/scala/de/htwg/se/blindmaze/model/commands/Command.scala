package de.htwg.se.blindmaze.model.commands

import de.htwg.se.blindmaze.controller.Controller
import de.htwg.se.blindmaze.model.Direction
import de.htwg.se.blindmaze.model.managers.GameManager
import scala.util.Try

// Command Pattern

trait Command {
  def execute(gameManager: GameManager): Try[GameManager] = Try(gameManager) // Default execute does nothing
  def undo(gameManager: GameManager): Try[GameManager] = Try(gameManager) // Default undo does nothing
  def redo(gameManager: GameManager): Try[GameManager] = execute(gameManager) // Default redo re-executes
}

case class MoveCommand(direction: Direction) extends Command {
  override def execute(gameManager: GameManager): Try[GameManager] = Try {
    gameManager.moveNext(direction)
  }

  override def undo(gameManager: GameManager): Try[GameManager] = {
    Try {
      gameManager.changeCurrent.moveNext(Direction.opposite(direction)).changeCurrent
    }
  }
}

case class StartGameCommand() extends Command {
  override def execute(gameManager: GameManager): Try[GameManager] = Try {
    gameManager.startGame
  }
}

case class QuitGameCommand() extends Command {
  override def execute(gameManager: GameManager): Try[GameManager] = Try {
    gameManager.quitGame
  }
}

case class InvalidCommand() extends Command {
  override def execute(gameManager: GameManager): Try[GameManager] = Try(gameManager)
}

case class UndoCommand() extends Command {
}