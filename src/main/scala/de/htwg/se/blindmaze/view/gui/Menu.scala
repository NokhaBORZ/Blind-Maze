package view.gui

import javafx.fxml.FXML
import view.gui.GUI

class Menu() {
  @FXML
  private def onQuitButton(): Unit = {
    GUI.stage.close()
  }
}