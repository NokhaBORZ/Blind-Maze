package de.htwg.se.blindmaze.view.gui

import javafx.fxml.FXML

import de.htwg.se.blindmaze.controller.Controller
import view.gui.GUI
import de.htwg.se.blindmaze.model.commands._


class ButtonController(gui: GUI, controller: Controller) {

  @FXML
  private def onStartButton(): Unit = {
    sendCommand(StartGameCommand())
  }


  @FXML
  private def onQuitButton(): Unit = {
    gui.stage.close()
    System.exit(0)
  }

  def sendCommand(command: Command): Unit = {
    controller.executeCommand(command)
  }
}