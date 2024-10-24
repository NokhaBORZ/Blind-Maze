// GridDisplay.scala
class GridDisplay(val grid: Grid, val playerManager: PlayerManager) {

  // Funktion zum Zeichnen des Grids mit allen Spielern
  def drawGrid(): Unit = {
    for (y <- 0 until grid.height) {
      for (x <- 0 until grid.width) {
        var playerFound = false

        // Überprüfen, ob sich ein Spieler an der Position (x, y) befindet
        for (i <- playerManager.players.indices) {
          val player = playerManager.players(i)
          if (player.x == x && player.y == y) {
            print(s"${i + 1} ") // Spieler anzeigen (P1, P2, P3, P4)
            playerFound = true
          }
        }

        // Wenn kein Spieler gefunden wurde, zeige den Zustand der Zelle an
        if (!playerFound) {
          val cell = grid.cells(y)(x)
          print(cell.display())
        }
      }
      println() // Neue Zeile am Ende jeder Reihe
    }
  }
}
