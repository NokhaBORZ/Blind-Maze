package view.gui

import scalafx.application.JFXApp3
import scalafx.application.ConditionalFeature.FXML
import javafx.fxml.{FXMLLoader, FXML}
import javafx.scene.Parent
import scalafx.scene.Scene
import scalafx.Includes.jfxParent2sfx
import scalafx.stage.Stage

object GUI extends JFXApp3 {

  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title = "Blind Maze"
      icons.add(new javafx.scene.image.Image("/image/icon.png"))
      fullScreen = true
    }

    switchScene("Menu")
  }

  // Load a scene from FXML
  def loadScene(sceneName: String, controller: Option[Any] = None): Scene = {
    val loader = new FXMLLoader(getClass.getResource(s"/fxml/$sceneName.fxml"))

    val root: Parent = loader.load()
    new Scene(root)
  }

  // Switch to a new scene
  def switchScene(sceneName: String, controller: Option[Any] = None): Unit = {
    stage.scene = loadScene(sceneName, controller)
    stage.show()
  }
}