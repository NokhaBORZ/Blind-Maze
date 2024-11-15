package de.htwg.se.blindmaze

import de.htwg.se.blindmaze.controller.Controller
import de.htwg.se.blindmaze.model.GameManager
import de.htwg.se.blindmaze.view.TUI

@main def blindmaze(): Unit = {
  
  val controller = Controller(GameManager())
  val tui = TUI(controller)

  var input = ""

  println("Enter command: n: new game, w: up, s: down, a: left, d: right, q: quit")
  while (input != "q") {
    input = scala.io.StdIn.readLine("Input: ")
    tui.processInputLine(input)
  }
}



