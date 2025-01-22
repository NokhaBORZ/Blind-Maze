package de.htwg.se.blindmaze.controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar
import de.htwg.se.blindmaze.model.managers.IGameManager
import de.htwg.se.blindmaze.model.commands.{ICommand, UndoCommand}
import scala.util.{Success, Failure}
import de.htwg.se.blindmaze.utils.GameEvent
import de.htwg.se.blindmaze.utils.{GameEvent, Observable}
import de.htwg.se.blindmaze.utils.Observer

class ControllerSpec extends AnyWordSpec with Matchers with MockitoSugar {

    "A Controller" should {

        "execute a command successfully" in {
            val gameManager = mock[IGameManager]
            val command = mock[ICommand]
            val newGameManager = mock[IGameManager]
            when(command.execute(gameManager)).thenReturn((Success(newGameManager), GameEvent.OnPlayerMoveEvent))

            val controller = new Controller(gameManager)
            controller.executeCommand(command)

            verify(command).execute(gameManager)
            controller.gameManager should be(newGameManager)
        }

        "handle command execution failure" in {
            val gameManager = mock[IGameManager]
            val command = mock[ICommand]
            when(command.execute(gameManager)).thenReturn((Failure(new Exception("Test exception")), GameEvent.OnErrorEvent("Test exception")))

            val controller = new Controller(gameManager)
            controller.executeCommand(command)

            verify(command).execute(gameManager)
            controller.gameManager should be(gameManager)
        }

        "undo the last command successfully" in {
            val gameManager = mock[IGameManager]
            val command = mock[ICommand]
            val newGameManager = mock[IGameManager]
            when(command.execute(gameManager)).thenReturn((Success(newGameManager), GameEvent.OnPlayerMoveEvent))
            when(command.undo(newGameManager)).thenReturn((Success(gameManager), GameEvent.OnUndoEvent))

            val controller = new Controller(gameManager)
            controller.executeCommand(command)
            controller.undo()

            verify(command).undo(newGameManager)
            controller.gameManager should be(gameManager)
        }

        "handle undo failure" in {
            val gameManager = mock[IGameManager]
            val command = mock[ICommand]
            val newGameManager = mock[IGameManager]
            when(command.execute(gameManager)).thenReturn((Success(newGameManager), GameEvent.OnPlayerMoveEvent))
            when(command.undo(newGameManager)).thenReturn((Failure(new Exception("Test exception")), GameEvent.OnErrorEvent("Test exception")))

            val controller = new Controller(gameManager)
            controller.executeCommand(command)
            controller.undo()

            verify(command).undo(newGameManager)
            controller.gameManager should be(newGameManager)
        }

        "not undo if there are no commands to undo" in {
            val gameManager = mock[IGameManager]
            val controller = new Controller(gameManager)
            controller.undo()

            controller.gameManager should be(gameManager)
        }

        "notify observers on command execution" in {
            val gameManager = mock[IGameManager]
            val command = mock[ICommand]
            val newGameManager = mock[IGameManager]
            when(command.execute(gameManager)).thenReturn((Success(newGameManager), GameEvent.OnPlayerMoveEvent))

            val controller = new Controller(gameManager)
            val observer = mock[Observer]
            controller.add(observer)

            controller.executeCommand(command)

            verify(observer).update(GameEvent.OnPlayerMoveEvent)
        }

        "notify observers on undo" in {
            val gameManager = mock[IGameManager]
            val command = mock[ICommand]
            val newGameManager = mock[IGameManager]
            when(command.execute(gameManager)).thenReturn((Success(newGameManager), GameEvent.OnPlayerMoveEvent))
            when(command.undo(newGameManager)).thenReturn((Success(gameManager), GameEvent.OnUndoEvent))

            val controller = new Controller(gameManager)
            val observer = mock[Observer]
            controller.add(observer)

            controller.executeCommand(command)
            controller.undo()

            verify(observer).update(GameEvent.OnUndoEvent)
        }
    }
}