object BlindMaze {
  def main(args: Array[String]): Unit = {
    println("Willkommen zum Unsichtbaren Maze-Spiel mit bis zu 4 Spielern!")
    var gameRunning = true

    // Erstelle ein Grid mit 40x20 Feldern
    val grid = new Grid(10, 10) // Grid-Größe 40x20
    val numPlayers = 1 // Anzahl der Spieler
    val playerManager = grid.playerManager
    playerManager.initializePlayers(grid.width, grid.height, grid) // Übergabe des Grids für Startposition

    // Verwende GridDisplay zum Anzeigen des Grids
    val gridDisplay = grid.gridDisplay

    var currentPlayer = 0

    while (gameRunning) {
      // Hole die Eingabe des aktuellen Spielers über den PlayerInputHandler
      PlayerInputHandler.getPlayerInput(currentPlayer) match {
        case Some(input) =>
          // Bewege den aktuellen Spieler basierend auf der Eingabe
          playerManager.movePlayer(currentPlayer, input, grid)

          // Überprüfe, ob der aktuelle Spieler das Ziel erreicht hat
          val (x, y) = playerManager.getPlayerPosition(currentPlayer)
          val (goalX, goalY) = grid.getGoalPosition()
          if (x == goalX && y == goalY) {
            println(s"Herzlichen Glueckwunsch, Spieler ${currentPlayer + 1}! Du hast das Ziel erreicht.")
            gameRunning = false
          } else {
            // Zum nächsten Spieler wechseln
            currentPlayer = (currentPlayer + 1) % numPlayers
          }

        case None =>
          // Beende das Spiel, wenn der Spieler 'exit' eingegeben hat
          gameRunning = false
      }

      // Zeichne das Grid nach jeder Runde
      gridDisplay.drawGrid()

      // Leere Zeile zur besseren Übersicht
      println()
    }
  }
}
