package de.htwg.se.blindmaze.model.commands

import de.htwg.se.blindmaze.controller.Controller
import de.htwg.se.blindmaze.model.Direction
import de.htwg.se.blindmaze.model.managers.GameManager
import scala.util.Try

// Command Pattern

trait Command {
  def execute(gameManager: GameManager): Try[GameManager]
  def undo(gameManager: GameManager): Try[GameManager] = Try(gameManager)
  def redo(gameManager: GameManager): Try[GameManager] = Try(gameManager)
}

case class MoveCommand(direction: Direction) extends Command {
  override def execute(gameManager: GameManager): Try[GameManager] = Try {
    gameManager.moveNext(direction)
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
  override def execute(gameManager: GameManager): Try[GameManager] = Try {
    gameManager.invalidCommand
  }
}
