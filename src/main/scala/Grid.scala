// Grid.scala
class Grid(val width: Int, val height: Int) {
  // Unsichtbares Ziel auf dem Grid
  private var goalX = 0
  private var goalY = 0

  // Das Grid wird von der Cell-Klasse verwaltet
  val cells: Array[Array[Cell]] = Array.fill(height, width)(Cell())

  // Spielerpositionen werden von der PlayerManager-Klasse verwaltet
  var playerManager = new PlayerManager()

  // Die GridDisplay-Klasse wird verwendet, um das Grid anzuzeigen
  var gridDisplay = new GridDisplay(this, playerManager)

  // Initialisierung: Spieler und Ziel setzen
  initializePlayersAndGoal()

  // Initialisiert die Spieler und das Ziel
  private def initializePlayersAndGoal(): Unit = {
    playerManager.initializePlayers(width, height, this)
    placeGoalAtEqualDistance()

    // Generiere zufällige Pfade mit MazeGeneration
    MazeGeneration.generatePaths(this, goalX, goalY, playerManager)
  }

  // Platzierung des Ziels auf dem Grid
  private def placeGoalAtEqualDistance(): Unit = {
    goalX = width / 2
    goalY = height / 2
  }

  def getGoalPosition(): (Int, Int) = (goalX, goalY)
}
