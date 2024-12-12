package de.htwg.se.blindmaze.controller

import de.htwg.se.blindmaze.model.managers.GameManager
import de.htwg.se.blindmaze.utils.Observable
import de.htwg.se.blindmaze.model.commands.{Command, UndoCommand}
import de.htwg.se.blindmaze.model.Direction
import scala.collection.mutable.Stack
import scala.util.{Try, Success, Failure}
import de.htwg.se.blindmaze.utils.GameEvent


class Controller(var gameManager: GameManager) extends Observable {
  private val commandHistory: Stack[Command] = Stack()

  def startGame(size: Int = 11): Unit = {
    gameManager = gameManager.startGame
    notifyObservers(GameEvent.OnPlayerMoveEvent)
  }

  def resetGame(): Unit = {
    gameManager = GameManager()
    notifyObservers(GameEvent.OnPlayerMoveEvent)
  }

  def movePlayer(direction: Direction): Unit = {
    gameManager = gameManager.moveNext(direction)
    notifyObservers(GameEvent.OnPlayerMoveEvent)
  }

  def showGrid: String = gameManager.showGrid

  def executeCommand(command: Command): Unit = {
    if (command.isInstanceOf[UndoCommand]) {
      undo()
      return
    }

    val input = command.execute(gameManager)

    input._1 match {
      case Success(newGameManager) =>
        gameManager = newGameManager
        commandHistory.push(command)
        notifyObservers(input._2)
      case Failure(exception) =>
        println(s"Command execution failed: ${exception.getMessage}")
        notifyObservers(input._2)
    }
  }

  def undo(): Unit = {
    if (commandHistory.nonEmpty) {
      val command = commandHistory.pop()
      val input = command.undo(gameManager)
       input._1 match {
        case Success(newGameManager) =>
          gameManager = newGameManager
          notifyObservers(input._2)
        case Failure(exception) =>
          println(s"Undo failed: ${exception.getMessage}")
      }
    } else {
      println("No commands to undo")
    }
  }
}