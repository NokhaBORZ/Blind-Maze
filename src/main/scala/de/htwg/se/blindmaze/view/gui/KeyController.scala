package de.htwg.se.blindmaze.view.gui

import javafx.scene.input.KeyEvent
import javafx.scene.input.KeyCode

import de.htwg.se.blindmaze.model.commands._
import de.htwg.se.blindmaze.utils.Direction

class KeyController(buttonController: ButtonController) {

    def handleKeyInput(event: KeyEvent): Unit = {
        event.getCode match {
            case KeyCode.W =>
                sendCommand(MoveCommand(Direction.Up, 1))
            case KeyCode.S =>
                sendCommand(MoveCommand(Direction.Down, 1))
            case KeyCode.A =>
                sendCommand(MoveCommand(Direction.Left, 1))
            case KeyCode.D =>
                sendCommand(MoveCommand(Direction.Right, 1))
            case KeyCode.UP =>
                sendCommand(MoveCommand(Direction.Up, 2))
            case KeyCode.DOWN =>
                sendCommand(MoveCommand(Direction.Down, 2))
            case KeyCode.LEFT =>
                sendCommand(MoveCommand(Direction.Left, 2))
            case KeyCode.RIGHT =>
                sendCommand(MoveCommand(Direction.Right, 2))
            case KeyCode.Q =>
                sendCommand(QuitGameCommand())
            case KeyCode.N =>
                sendCommand(StartGameCommand())
            case KeyCode.K =>
                sendCommand(SaveCommand())
            case KeyCode.L =>
                sendCommand(LoadCommand())
            case _ => 
        }
    }
    private def sendCommand(command: ICommand): Unit = {
        buttonController.sendCommand(command)
    }
}