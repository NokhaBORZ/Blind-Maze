package de.htwg.se.blindmaze.view

import de.htwg.se.blindmaze.utils.Observer
import de.htwg.se.blindmaze.controller.Controller
import de.htwg.se.blindmaze.model.Direction

class TUI (controller: Controller) extends Observer {
  controller.add(this)
  def update(): Unit = {
    println(controller.showGrid)
    println(controller.gameManager.getCurrent)
    println("Enter command: n: new game, w: up, s: down, a: left, d: right, q: quit")
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

