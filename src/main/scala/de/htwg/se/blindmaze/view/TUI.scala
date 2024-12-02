package de.htwg.se.blindmaze.view

import scala.io.AnsiColor.{GREEN, RED, RESET}
import de.htwg.se.blindmaze.utils.Observer
import de.htwg.se.blindmaze.controller.Controller
import de.htwg.se.blindmaze.model.Direction
import de.htwg.se.blindmaze.model.commands._

class TUI (controller: Controller) extends Observer {
  controller.add(this)
  def update(): Unit = {
    println(controller.showGrid)
    println("Current player: " + controller.gameManager.current)
    println(" ")
    println("w,a,s,d for movement.")
    println("Use 'q' to quit.")
    println("")
  }

  def processInputLine(input: String): Unit = {
    val command: Command = input match {
      case "q" => new QuitGameCommand
      case "n" => new StartGameCommand
      case "w" => new MoveUpCommand
      case "s" => new MoveDownCommand
      case "a" => new MoveLeftCommand
      case "d" => new MoveRightCommand
      case _ => new InvalidCommand
    }
    controller.executeCommand(command)
  }
}
