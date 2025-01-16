package de.htwg.se.blindmaze

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._

import de.htwg.se.blindmaze.modules.AppModule

import scala.io.AnsiColor.{GREEN, RED, RESET}
import de.htwg.se.blindmaze.controller.Controller
import de.htwg.se.blindmaze.model.managers.IGameManager
import de.htwg.se.blindmaze.view.TUI
import _root_.view.gui.GUI

@main def blindmaze(): Unit = {
  val injector = Guice.createInjector(new AppModule)
  val controller = Controller(injector.instance[IGameManager])
  val tui = TUI(controller)
  val gui = GUI(controller)
  
  new Thread(() => {
    gui.main(Array.empty)
  }).start()

  var input = ""

  println(s"${GREEN}The Game Blind Maze!${RESET}")
  println("Find your way through the maze to the exit.")
  println("'n' for new game.")
  println("w,a,s,d for movement.")
  println("Use 'q' to quit.")
  println("")

  while (input != "q") {
    input = scala.io.StdIn.readLine("Input: ")
    tui.processInputLine(input)
  }
}



