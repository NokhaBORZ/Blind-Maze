package de.htwg.se.blindmaze.model.commands

import de.htwg.se.blindmaze.controller.Controller
import de.htwg.se.blindmaze.model.Direction

//Command Pattern

trait Command {
  def execute(controller: Controller): Unit
}

class MoveUpCommand extends Command {
  override def execute(controller: Controller): Unit = {
    controller.movePlayer(Direction.Up)
  }
}

class MoveDownCommand extends Command {
  override def execute(controller: Controller): Unit = {
    controller.movePlayer(Direction.Down)
  }
}

class MoveLeftCommand extends Command {
  override def execute(controller: Controller): Unit = {
    controller.movePlayer(Direction.Left)
  }
}

class MoveRightCommand extends Command {
  override def execute(controller: Controller): Unit = {
    controller.movePlayer(Direction.Right)
  }
}

class StartGameCommand extends Command {
  override def execute(controller: Controller): Unit = {
    controller.startGame()
  }
}

class QuitGameCommand extends Command {
  override def execute(controller: Controller): Unit = {
    println("Quit game")
  }
}

class InvalidCommand extends Command {
  override def execute(controller: Controller): Unit = {
    println("Invalid input")
  }
}
