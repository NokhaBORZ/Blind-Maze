// PlayerManager.scala
class PlayerManager {
  var players: Array[Player] = Array()

  // Initialisiert die Spieler mit zufälligen Positionen
  def initializePlayers(gridWidth: Int, gridHeight: Int, grid: Grid): Unit = {
    players = Array(
      Player(0, 0, "P1"),
      Player(gridWidth - 1, gridHeight - 1, "P2"),
      Player(0, gridHeight - 1, "P3"),
      Player(gridWidth - 1, 0, "P4")
    )

    // Markiere die Startpositionen der Spieler als besucht
    for (player <- players) {
      grid.cells(player.y)(player.x).setPlayer()   // Spielerposition setzen
      grid.cells(player.y)(player.x).visit()       // Startzelle als besucht markieren
    }
  }

  // Bewegt einen Spieler und aktualisiert den Zellenzustand
  def movePlayer(playerIndex: Int, direction: String, grid: Grid): Unit = {
    val player = players(playerIndex)

    // Entferne den Spieler von der aktuellen Zelle und markiere sie als besucht
    grid.cells(player.y)(player.x).removePlayer()

    // Bewege den Spieler
    player.move(direction, grid)

    // Setze den Spieler auf die neue Zelle
    grid.cells(player.y)(player.x).setPlayer()

    // Markiere die neue Position als besucht
    grid.cells(player.y)(player.x).visit()
  }

  def getPlayerPosition(playerIndex: Int): (Int, Int) = {
    (players(playerIndex).x, players(playerIndex).y)
  }
}
