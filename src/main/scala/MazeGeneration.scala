import scala.collection.mutable
import scala.util.Random
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

    println(s"Starte Maze-Generierung mit ${playerManager.players.length} Spielern.")

    // Startzustand für jeden Spieler in die Queue einfügen
    for (i <- playerManager.players.indices) {
      val (px, py) = playerManager.getPlayerPosition(i)
      queue.enqueue(((px, py), i))
      grid.cells(py)(px).setPath() // Setzt die Spielerposition als Pfad
      visited(py)(px) = true
      println(s"Spieler $i startet bei Position ($px, $py).")
    }

    // BFS für alle Spieler gleichzeitig starten, um gleich lange Pfade zu erzeugen
    var steps = 0
    while (queue.nonEmpty) {
      val ((x, y), playerIndex) = queue.dequeue()

      steps += 1
      println(s"Schritt $steps: Verarbeite Position ($x, $y) für Spieler $playerIndex.")

      // Für jede Richtung prüfen
      random.shuffle(directions).foreach {
        case (dx, dy) =>
          val newX = x + dx
          val newY = y + dy

          // Prüfe, ob die Bewegung im Grid bleibt und die Zelle noch nicht besucht wurde
          if (newX >= 0 && newX < grid.width && newY >= 0 && newY < grid.height && !visited(newY)(newX)) {
            // Zufällig entscheiden, ob der Pfad ein echter, Fake oder WeakPath ist
            val pathType = random.nextInt(3) // 0 = Path, 1 = Fake, 2 = WeakPath
            pathType match {
              case 0 => 
                grid.cells(newY)(newX).setPath()    // Normales Path
                println(s"Erzeuge Pfad an Position ($newX, $newY).")
              case 1 => 
                grid.cells(newY)(newX).setFake()    // Fake Path
                println(s"Erzeuge Fake-Pfad an Position ($newX, $newY).")
              case 2 => 
                grid.cells(newY)(newX).setWeakPath()// Weak Path (nur einmal benutzbar)
                println(s"Erzeuge Weak-Pfad an Position ($newX, $newY).")
            }

            visited(newY)(newX) = true
            queue.enqueue(((newX, newY), playerIndex))
          }
      }

      // Wenn die Schrittezahl sehr groß wird, breche ab, um endlose Schleifen zu vermeiden
      if (steps > 10000) {
        println("Abbruch nach 10.000 Schritten, um eine Endlosschleife zu vermeiden.")
        return
      }
    }

    // Erstelle zufällige Pfade, bis alle Spieler das Ziel erreichen können
    ensureGoalConnection(grid, goalX, goalY, playerManager)
    println("Maze-Generierung abgeschlossen.")
  }

  // Methode zur Sicherstellung, dass das Ziel mit allen Pfaden verbunden ist
  private def ensureGoalConnection(grid: Grid, goalX: Int, goalY: Int, playerManager: PlayerManager): Unit = {
    val directions = List((0, 1), (1, 0), (0, -1), (-1, 0))

    // Das Ziel muss auf einem Pfad liegen
    grid.cells(goalY)(goalX).setPath()
    println(s"Das Ziel ist bei Position ($goalX, $goalY) gesetzt.")

    // Verbinde das Ziel mit dem Pfad der Spieler
    for (i <- playerManager.players.indices) {
      var (px, py) = playerManager.getPlayerPosition(i)
      println(s"Verbinde Spieler $i mit dem Ziel.")

      while (px != goalX || py != goalY) {
        val possibleMoves = directions.filter {
          case (dx, dy) =>
            val newX = px + dx
            val newY = py + dy
            newX >= 0 && newX < grid.width && newY >= 0 && newY < grid.height && grid.cells(newY)(newX).state == CellState.Path
        }

        // Wähle eine zufällige Bewegung in Richtung des Ziels
        if (possibleMoves.nonEmpty) {
          val (dx, dy) = Random.shuffle(possibleMoves).head
          px += dx
          py += dy
          grid.cells(py)(px).setPath() // Markiere den Pfad als begehbar
          println(s"Spieler $i bewegt sich zu Position ($px, $py).")
        }
      }
    }
    println("Alle Spieler sind mit dem Ziel verbunden.")
  }
}
