package de.htwg.se.blindmaze.view.gui

import javafx.scene.input.KeyEvent
import javafx.scene.input.KeyCode

import de.htwg.se.blindmaze.model.commands._
import de.htwg.se.blindmaze.utils.Direction

class KeyController(buttonController: ButtonController) {

    def handleKeyInput(event: KeyEvent): Unit = {
        event.getCode match {
            case KeyCode.W =>
                sendCommand(MoveCommand(Direction.Up))
            case KeyCode.S =>
                sendCommand(MoveCommand(Direction.Down))
            case KeyCode.A =>
                sendCommand(MoveCommand(Direction.Left))
            case KeyCode.D =>
                sendCommand(MoveCommand(Direction.Right))
            case KeyCode.UP =>
                sendCommand(MoveCommand(Direction.Up))
            case KeyCode.DOWN =>
                sendCommand(MoveCommand(Direction.Down))
            case KeyCode.LEFT =>
                sendCommand(MoveCommand(Direction.Left))
            case KeyCode.RIGHT =>
                sendCommand(MoveCommand(Direction.Right))
            case KeyCode.Q =>
                sendCommand(QuitGameCommand())
            case KeyCode.N =>
                sendCommand(StartGameCommand())
            case _ => 
        }
    }
    private def sendCommand(command: ICommand): Unit = {
        buttonController.sendCommand(command)
    }
}