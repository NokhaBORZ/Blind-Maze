package de.htwg.se.blindmaze.controller

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._

import de.htwg.se.blindmaze.modules.AppModule

import de.htwg.se.blindmaze.model.managers.IGameManager
import de.htwg.se.blindmaze.utils.Observable
import de.htwg.se.blindmaze.model.commands.{ICommand, UndoCommand}
import de.htwg.se.blindmaze.utils.Direction
import scala.collection.mutable.Stack
import scala.util.{Try, Success, Failure}
import de.htwg.se.blindmaze.utils.GameEvent


class Controller(var gameManager: IGameManager) extends Observable {
  val injector = Guice.createInjector(new AppModule)
  private val commandHistory: Stack[ICommand] = Stack()

  def showGrid: String = gameManager.showGrid

  def executeCommand(command: ICommand): Unit = {
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