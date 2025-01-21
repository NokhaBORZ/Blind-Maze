package de.htwg.se.blindmaze.view

import scala.io.AnsiColor.{GREEN, RED, RESET}
import de.htwg.se.blindmaze.utils.Observer
import de.htwg.se.blindmaze.controller.Controller
import de.htwg.se.blindmaze.utils.Direction
import de.htwg.se.blindmaze.model.commands._
import de.htwg.se.blindmaze.utils.GameEvent

class TUI (controller: Controller) extends Observer {
  controller.add(this)
  def update(event: GameEvent): Unit = {
    println(controller.showGrid)
    if (event != GameEvent.OnQuitEvent) {

    println("Current player: " + controller.gameManager.current)
    println(" ")
    println("w,a,s,d for movement.")
    println("Use 'q' to quit.")
    println("")
    }
  }

  def processInputLine(input: String): Unit = {
    val command: ICommand = input match {
      case "q" => QuitGameCommand()
      case "n" => StartGameCommand()
      case "w" => MoveCommand(Direction.Up, 1)
      case "s" => MoveCommand(Direction.Down, 1)
      case "a" => MoveCommand(Direction.Left, 1)
      case "d" => MoveCommand(Direction.Right, 1)
      case "i" => MoveCommand(Direction.Up, 2)
      case "l" => MoveCommand(Direction.Right, 2)
      case "k" => MoveCommand(Direction.Down, 2)
      case "j" => MoveCommand(Direction.Left, 2)
      case "u" => UndoCommand()
      case "save" => SaveCommand()
      case "load" => LoadCommand()
      case _ => new InvalidCommand
    }
    controller.executeCommand(command)
  }
}
