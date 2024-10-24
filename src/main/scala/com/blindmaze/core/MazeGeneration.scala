import scala.collection.mutable
import scala.util.Random
import scala.util.control.Breaks._
import CellState._

object MazeGeneration {

  // Methode zur Generierung der Pfade vom Spieler zum Ziel
  def generatePaths(grid: Grid, goalX: Int, goalY: Int, playerManager: PlayerManager): Unit = {
    val directions = List((0, 1), (1, 0), (0, -1), (-1, 0)) // Richtung: unten, rechts, oben, links
    val random = new Random()

    // Queue für BFS: ((x, y), SpielerIndex)
    val queue = mutable.Queue[((Int, Int), Int)]()

    // Besuchte Zellen nach Spieler
    val visited = Array.fill(grid.height, grid.width)(false)

    // Startzustand für jeden Spieler in die Queue einfügen
    for (i <- playerManager.players.indices) {
      val (px, py) = playerManager.getPlayerPosition(i)
      queue.enqueue(((px, py), i))
      grid.cells(py)(px).setPath() // Setzt die Spielerposition als Pfad
      visited(py)(px) = true
    }

    // BFS für alle Spieler gleichzeitig starten, um gleich lange Pfade zu erzeugen
    var steps = 0
    while (queue.nonEmpty) {
      val ((x, y), playerIndex) = queue.dequeue()

      // Für jede Richtung prüfen
      random.shuffle(directions).foreach {
        case (dx, dy) =>
          val newX = x + dx
          val newY = y + dy

          // Prüfe, ob die Bewegung im Grid bleibt und die Zelle noch nicht besucht wurde
          if (newX >= 0 && newX < grid.width && newY >= 0 && newY < grid.height && !visited(newY)(newX)) {
            // Entscheide, ob der Pfad ein echter, Fake oder WeakPath ist
            // Fake-Pfade nur dort setzen, wo sie nicht blockieren
            if (!isOnMainPath(newX, newY, playerManager, grid)) {
              val pathType = random.nextInt(3) // 0 = Path, 1 = Fake, 2 = WeakPath
              pathType match {
                case 0 => grid.cells(newY)(newX).setPath()    // Normales Path
                case 1 => grid.cells(newY)(newX).setFake()    // Fake Path, nur wenn es kein kritischer Weg ist
                case 2 => grid.cells(newY)(newX).setWeakPath()// Weak Path (nur einmal benutzbar)
              }
            } else {
              grid.cells(newY)(newX).setPath() // Setze den Pfad, wenn es auf dem Hauptpfad liegt
            }

            visited(newY)(newX) = true
            queue.enqueue(((newX, newY), playerIndex))
          }
      }

      // Wenn die Schrittezahl sehr groß wird, breche ab, um endlose Schleifen zu vermeiden
      if (steps > 10000) return
    }

    // Erstelle zufällige Pfade, bis alle Spieler das Ziel erreichen können
    ensureGoalConnection(grid, goalX, goalY, playerManager)
  }

  // Methode zur Überprüfung, ob eine Position Teil des Hauptpfades ist
  private def isOnMainPath(x: Int, y: Int, playerManager: PlayerManager, grid: Grid): Boolean = {
    // Überprüfe, ob die Position (x, y) auf einem kritischen Pfad (Spieler -> Ziel) liegt
    // Spieler dürfen nicht blockiert werden, wenn sie auf das Ziel zulaufen
    for (i <- playerManager.players.indices) {
      val (playerX, playerY) = playerManager.getPlayerPosition(i)
      if (grid.cells(playerY)(playerX).state == CellState.Path) {
        if ((x == playerX && y == playerY) || grid.cells(y)(x).state == CellState.Path) {
          return true // Direktes Zurückgeben von true, ohne return
        }
      }
    }
    false // Wenn keine passende Bedingung erfüllt ist, gib false zurück
  }

  // Methode zur Sicherstellung, dass das Ziel mit allen Pfaden verbunden ist
  private def ensureGoalConnection(grid: Grid, goalX: Int, goalY: Int, playerManager: PlayerManager): Unit = {
    val directions = List((0, 1), (1, 0), (0, -1), (-1, 0)) // Bewegung in 4 Richtungen
    val visited = Array.fill(grid.height, grid.width)(false) // Verfolgung besuchter Positionen

    // Das Ziel muss auf einem Pfad liegen
    grid.cells(goalY)(goalX).setPath()

    // Verbinde das Ziel mit dem Pfad der Spieler
    for (i <- playerManager.players.indices) {
      var (px, py) = playerManager.getPlayerPosition(i)

      // Verwende breakable, um eine Schleife kontrolliert abzubrechen
      breakable {
        // Stelle sicher, dass der Spieler nicht in einer Schleife hängen bleibt
        while ((px, py) != (goalX, goalY)) {
          visited(py)(px) = true // Markiere die aktuelle Position als besucht

          // Filtere Bewegungen, die das Ziel näher bringen und die Zelle nicht bereits besucht wurde
          val possibleMoves = directions.filter {
            case (dx, dy) =>
              val newX = px + dx
              val newY = py + dy
              newX >= 0 && newX < grid.width && newY >= 0 && newY < grid.height && !visited(newY)(newX) && 
              grid.cells(newY)(newX).state == CellState.Path
          }

          // Überprüfe, ob es gültige Bewegungen gibt
          if (possibleMoves.nonEmpty) {
            val (dx, dy) = Random.shuffle(possibleMoves).head
            px += dx
            py += dy
            grid.cells(py)(px).setPath() // Markiere den Pfad als begehbar
          } else {
            // Falls keine gültigen Bewegungen mehr möglich sind, breche die Schleife
            break // Verwende break, um die Schleife zu verlassen
          }
        }
      }
    }
  }
}
