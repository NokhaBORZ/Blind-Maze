package de.htwg.se.blindmaze.model.commands

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar
import de.htwg.se.blindmaze.model.managers.IGameManager
import scala.util.{Success, Failure}
import de.htwg.se.blindmaze.utils.{GameEvent, Direction}
import de.htwg.se.blindmaze.model.fileIO.IFileIO

class CommandSpec extends AnyWordSpec with Matchers with MockitoSugar {

    "A MoveCommand" should {
        "execute successfully" in {
            val gameManager = mock[IGameManager]
            val newGameManager = mock[IGameManager]
            when(gameManager.moveNext(Direction.Up, 1)).thenReturn(newGameManager)
            val command = MoveCommand(Direction.Up, 1)

            val result = command.execute(gameManager)

            result._1 should be(Success(newGameManager))
            result._2 should be(GameEvent.OnPlayerMoveEvent)
        }

        "undo successfully" in {
            val gameManager = mock[IGameManager]
            val newGameManager = mock[IGameManager]
            when(gameManager.changeCurrent).thenReturn(gameManager)
            when(gameManager.moveNext(Direction.Down, 1)).thenReturn(newGameManager)
            val command = MoveCommand(Direction.Up, 1)

            val result = command.undo(gameManager)

            result._1 should be(Success(newGameManager))
            result._2 should be(GameEvent.OnUndoEvent)
        }
    }

    "A StartGameCommand" should {
        "execute successfully" in {
            val gameManager = mock[IGameManager]
            when(gameManager.startGame).thenReturn(gameManager)
            val command = StartGameCommand()

            val result = command.execute(gameManager)

            result._1 should be(Success(gameManager))
            result._2 should be(GameEvent.OnGameStartEvent)
        }
    }

    "A QuitGameCommand" should {
        "execute successfully" in {
            val gameManager = mock[IGameManager]
            when(gameManager.quitGame).thenReturn(gameManager)
            val command = QuitGameCommand()

            val result = command.execute(gameManager)

            result._1 should be(Success(gameManager))
            result._2 should be(GameEvent.OnQuitEvent)
        }
    }

    "An InvalidCommand" should {
        "fail to execute" in {
            val gameManager = mock[IGameManager]
            val command = InvalidCommand()

            val result = command.execute(gameManager)

            result._1.isFailure should be(true)
            result._2 should be(GameEvent.OnErrorEvent("Invalid command"))
        }
    }

    "A SaveCommand" should {
        "execute successfully" in {
            val gameManager = mock[IGameManager]
            val loader = mock[IFileIO]
            val command = SaveCommand()

            doNothing().when(loader).save(gameManager)

            val result = command.execute(gameManager)

            result._1 should be(Success(gameManager))
            result._2 should be(GameEvent.OnSaveEvent)
        }
    }

    // "A LoadCommand" should {

    "An UndoCommand" should {
        "do nothing on execute" in {
            val gameManager = mock[IGameManager]
            val command = UndoCommand()

            val result = command.execute(gameManager)

            result._1.isFailure should be(true)
            result._2 should be(GameEvent.OnErrorEvent("None existent"))
        }
    }
}