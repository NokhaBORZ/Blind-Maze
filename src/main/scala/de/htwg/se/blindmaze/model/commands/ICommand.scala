package de.htwg.se.blindmaze.model.commands

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.blindmaze.modules.AppModule

import de.htwg.se.blindmaze.controller.Controller
import de.htwg.se.blindmaze.utils.Direction
import de.htwg.se.blindmaze.model.managers.{IGameManager, GameState}
import scala.util.{Try, Success, Failure}
import de.htwg.se.blindmaze.utils.GameEvent
import scala.util.Failure
import de.htwg.se.blindmaze.model.fileIO.IFileIO

// Command Pattern

trait ICommand {

  val injector = Guice.createInjector(new AppModule)

  def execute(gameManager: IGameManager): (Try[IGameManager], GameEvent)
    = (Failure(new Exception), GameEvent.OnErrorEvent("None existent"))
  def undo(gameManager: IGameManager): (Try[IGameManager], GameEvent)
    = (Failure(new Exception), GameEvent.OnErrorEvent("None existent"))
  def redo(gameManager: IGameManager): (Try[IGameManager], GameEvent) 
    = (Failure(new Exception), GameEvent.OnErrorEvent("None existent"))

  val loader: IFileIO = injector.instance[IFileIO](Names.named("Json"))
}

case class MoveCommand(direction: Direction, playerId: Int) extends ICommand {
  override def execute(gameManager: IGameManager): (Try[IGameManager], GameEvent) = {
    val newGameManager = gameManager.moveNext(direction, playerId)
    val event = if (newGameManager.state == GameState.Finished) {
      GameEvent.OnPlayerWinEvent(newGameManager.current)
    } else {
      GameEvent.OnPlayerMoveEvent
    }
    (Try(newGameManager), event)
  }

  override def undo(gameManager: IGameManager): (Try[IGameManager], GameEvent) = {
      (Try(gameManager.changeCurrent.moveNext(Direction.opposite(direction), playerId)), GameEvent.OnUndoEvent)
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

case class SaveCommand() extends ICommand {
  override def execute(gameManager: IGameManager): (Try[IGameManager], GameEvent) = {
    println("Saving game...")
    loader.save(gameManager) 
    (Try(gameManager), GameEvent.OnSaveEvent)
  }
}

case class LoadCommand() extends ICommand {
  override def execute(gameManager: IGameManager): (Try[IGameManager], GameEvent) = {
    println("Loading game...")
    val newGameManager = loader.load
    (Try(newGameManager), GameEvent.OnLoadEvent)
  }
}

case class UndoCommand() extends ICommand {
}