package de.htwg.se.blindmaze.model.commands

import de.htwg.se.blindmaze.controller.Controller
import de.htwg.se.blindmaze.model.Direction
import de.htwg.se.blindmaze.model.managers.GameManager
import scala.util.Try
import de.htwg.se.blindmaze.utils.GameEvent
import scala.util.Failure

// Command Pattern

trait Command {
  def execute(gameManager: GameManager): (Try[GameManager], GameEvent)
    = (Failure(new Exception), GameEvent.OnErrorEvent("None existent"))
  def undo(gameManager: GameManager): (Try[GameManager], GameEvent)
    = (Failure(new Exception), GameEvent.OnErrorEvent("None existent"))
  def redo(gameManager: GameManager): (Try[GameManager], GameEvent) 
    = (Failure(new Exception), GameEvent.OnErrorEvent("None existent"))
}

case class MoveCommand(direction: Direction) extends Command {
  override def execute(gameManager: GameManager): (Try[GameManager], GameEvent) =  {
    (Try(gameManager.moveNext(direction)), GameEvent.OnPlayerMoveEvent)
  }

  override def undo(gameManager: GameManager): (Try[GameManager], GameEvent) = {
      (Try(gameManager.changeCurrent.moveNext(Direction.opposite(direction))), GameEvent.OnUndoEvent)
  }
}

case class StartGameCommand() extends Command {
  override def execute(gameManager: GameManager): (Try[GameManager], GameEvent) = {
    (Try(gameManager.startGame), GameEvent.OnGameStartEvent)
  }
}

case class QuitGameCommand() extends Command {
  override def execute(gameManager: GameManager): (Try[GameManager], GameEvent) = {
    (Try(gameManager.quitGame), GameEvent.OnQuitEvent)
  }
}

case class InvalidCommand() extends Command {
  override def execute(gameManager: GameManager): (Try[GameManager], GameEvent) 
  = (Failure(new Exception), GameEvent.OnErrorEvent("Invalid command"))
}

case class UndoCommand() extends Command {
}