package view.gui

import javafx.scene.layout.StackPane
import javafx.fxml.{FXMLLoader, FXML}
import javafx.scene.Parent
import javafx.scene.layout.BorderPane
import javafx.scene.input.KeyEvent
import javafx.application.Platform


import scalafx.application.JFXApp3
import scalafx.application.ConditionalFeature.FXML
import scalafx.scene.Scene
import scalafx.Includes.jfxParent2sfx
import scalafx.stage.Stage

import de.htwg.se.blindmaze.controller.Controller
import de.htwg.se.blindmaze.view.gui.ButtonController
import de.htwg.se.blindmaze.utils.GUIrenderer
import de.htwg.se.blindmaze.view.gui.KeyController
import de.htwg.se.blindmaze.utils.Observer
import de.htwg.se.blindmaze.utils.GameEvent
import de.htwg.se.blindmaze.utils.AudioManager

class GUI(controller: Controller) extends JFXApp3 with Observer {

  controller.add(this)
  override def update(event: GameEvent): Unit = {
    Platform.runLater(() => {
      event match {
      case GameEvent.OnGameStartEvent => 
        switchScene("Game")
        showGrid()
      case GameEvent.OnPlayerMoveEvent =>
        showGrid()
      case GameEvent.OnQuitEvent =>
        Platform.exit()
        System.exit(0)
      case GameEvent.OnLoadEvent =>
        switchScene("Game")
        showGrid()
      case GameEvent.OnPlayerWinEvent(p) =>
        showGrid()
        showWinnerPanel(p)
      case _ =>
      }
    })
  }

  private def showWinnerPanel(player: Int): Unit = {
    rootPane.getChildren.add(GUIrenderer.renderWinner(player)) 
  }

  val buttonController = new ButtonController(this, controller)
  val keyController = new KeyController(buttonController)
  val rootPane = new StackPane()

  override def start(): Unit = {
    AudioManager.initialize()
    stage = new JFXApp3.PrimaryStage {
      title = "Blind Maze"
      icons.add(new javafx.scene.image.Image("/image/icon.png"))
      fullScreen = true
      fullScreenExitHint = ""
      scene = new Scene(rootPane, 800, 600) {
        onKeyPressed = (event: KeyEvent) => keyController.handleKeyInput(event)
      }
    }

    switchScene("Menu")
  }

  def showGrid(): Unit = {
    Platform.runLater(new Runnable {
      override def run(): Unit = {
        val borderPane = rootPane.getChildren().getFirst().asInstanceOf[BorderPane]
        if (borderPane == null) 
          return

        val grid = GUIrenderer.render(controller.gameManager.grid, controller.gameManager.current,
         rootPane.getWidth(), rootPane.getHeight())
        borderPane.setCenter(grid)
      }
    })
  }

  // Switch to a new scene
  def switchScene(sceneName: String): Unit = {
    val newRoot = loadFXML(sceneName)
    rootPane.getChildren.clear()
    rootPane.getChildren.add(newRoot)
  }

  // Load a scene from FXML
  private def loadFXML(
      sceneName: String
  ): Parent = {
    val loader = new FXMLLoader(getClass.getResource(s"/fxml/$sceneName.fxml"))
    loader.setController(buttonController)
    loader.load[Parent]()
  }
}