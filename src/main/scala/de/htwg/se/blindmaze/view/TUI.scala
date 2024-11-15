package de.htwg.se.blindmaze.view

import scala.io.AnsiColor.{GREEN, RED, RESET}
import de.htwg.se.blindmaze.utils.Observer
import de.htwg.se.blindmaze.controller.Controller
import de.htwg.se.blindmaze.model.Direction

class TUI (controller: Controller) extends Observer {
  controller.add(this)
  def update(): Unit = {
    println(controller.showGrid)
    println(controller.gameManager.getCurrent)
    println(" ")
    println("w,a,s,d for movement.")
    println("Use 'q' to quit.")
    println("")
  }

  def processInputLine(input: String): Unit = {
    input match {
      case "q" => println("Quit game")
      case "n" => controller.startGame()
      case "w" => controller.movePlayer(Direction.Up)
      case "s" => controller.movePlayer(Direction.Down)
      case "a" => controller.movePlayer(Direction.Left)
      case "d" => controller.movePlayer(Direction.Right)
      case _ => println("Invalid input")
    }
  }
}

