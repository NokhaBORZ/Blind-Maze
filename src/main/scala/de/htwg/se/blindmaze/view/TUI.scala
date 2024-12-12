package de.htwg.se.blindmaze.view

import scala.io.AnsiColor.{GREEN, RED, RESET}
import de.htwg.se.blindmaze.utils.Observer
import de.htwg.se.blindmaze.controller.Controller
import de.htwg.se.blindmaze.model.Direction
import de.htwg.se.blindmaze.model.commands._
import de.htwg.se.blindmaze.utils.GameEvent

class TUI (controller: Controller) extends Observer {
  controller.add(this)
  def update(event: GameEvent): Unit = {
    println(controller.showGrid)
    println("Current player: " + controller.gameManager.current)
    println(" ")
    println("w,a,s,d for movement.")
    println("Use 'q' to quit.")
    println("")
  }

  def processInputLine(input: String): Unit = {
    val command: Command = input match {
      case "q" => QuitGameCommand()
      case "n" => StartGameCommand()
      case "w" => MoveCommand(Direction.Up)
      case "s" => MoveCommand(Direction.Down)
      case "a" => MoveCommand(Direction.Left)
      case "d" => MoveCommand(Direction.Right)
      case "u" => UndoCommand()
      case _ => new InvalidCommand
    }
    controller.executeCommand(command)
  }
}
