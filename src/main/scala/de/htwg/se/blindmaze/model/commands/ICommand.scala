package de.htwg.se.blindmaze.model.commands

import de.htwg.se.blindmaze.controller.Controller
import de.htwg.se.blindmaze.utils.Direction
import de.htwg.se.blindmaze.model.managers.IGameManager
import scala.util.Try
import de.htwg.se.blindmaze.utils.GameEvent
import scala.util.Failure

// Command Pattern

trait ICommand {
  def execute(gameManager: IGameManager): (Try[IGameManager], GameEvent)
    = (Failure(new Exception), GameEvent.OnErrorEvent("None existent"))
  def undo(gameManager: IGameManager): (Try[IGameManager], GameEvent)
    = (Failure(new Exception), GameEvent.OnErrorEvent("None existent"))
  def redo(gameManager: IGameManager): (Try[IGameManager], GameEvent) 
    = (Failure(new Exception), GameEvent.OnErrorEvent("None existent"))
}

case class MoveCommand(direction: Direction) extends ICommand {
  override def execute(gameManager: IGameManager): (Try[IGameManager], GameEvent) =  {
    (Try(gameManager.moveNext(direction)), GameEvent.OnPlayerMoveEvent)
  }

  override def undo(gameManager: IGameManager): (Try[IGameManager], GameEvent) = {
      (Try(gameManager.changeCurrent.moveNext(Direction.opposite(direction))), GameEvent.OnUndoEvent)
  }
}

case class StartGameCommand() extends ICommand {
  override def execute(gameManager: IGameManager): (Try[IGameManager], GameEvent) = {
    (Try(gameManager.startGame), GameEvent.OnGameStartEvent)
  }
}

case class QuitGameCommand() extends ICommand {
  override def execute(gameManager: IGameManager): (Try[IGameManager], GameEvent) = {
    (Try(gameManager.quitGame), GameEvent.OnQuitEvent)
  }
}

case class InvalidCommand() extends ICommand {
  override def execute(gameManager: IGameManager): (Try[IGameManager], GameEvent) 
  = (Failure(new Exception), GameEvent.OnErrorEvent("Invalid command"))
}

case class UndoCommand() extends ICommand {
}