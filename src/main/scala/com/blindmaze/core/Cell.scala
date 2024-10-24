// Cell.scala
import CellState._

case class Cell(var state: CellState = Empty, var weakPathUsed: Boolean = false) {

  // Setzt den Zustand auf normalen Pfad
  def setPath(): Unit = {
    state = Path
  }

  // Setzt den Zustand auf Fake (täuscht den Spieler)
  def setFake(): Unit = {
    state = Fake
  }

  // Setzt den Zustand auf schwachen Pfad (nur einmal benutzbar)
  def setWeakPath(): Unit = {
    state = WeakPath
  }

  // Markiert die Zelle als besucht
  def visit(): Unit = {
    state = Visited
  }

  // Markiert die Zelle als Spielerposition
  def setPlayer(): Unit = {
    state = Player
  }

  // Entfernt den Spieler von der Zelle
  def removePlayer(): Unit = {
    if (state == Player) {
      state = if (weakPathUsed) Empty else Visited // Schwacher Pfad wird unpassierbar
    }
  }

  // Prüft, ob die Zelle passierbar ist
  def isPassable(): Boolean = state match {
    case Path | Visited | WeakPath if !weakPathUsed => true // Schwacher Pfad darf nur einmal betreten werden
    case _ => false
  }

  // Gibt das Symbol zur Darstellung der Zelle zurück
  def display(): String = state match {
    case Empty => "  "
    case Path => ". "
    case Visited => "X "
    case Player => "P "
    case Fake => "F " // Fake-Pfad anzeigen
    case WeakPath => "W " // Schwacher Pfad anzeigen
  }

  // Diese Methode wird aufgerufen, wenn ein Spieler einen WeakPath betritt
  def useWeakPath(): Unit = {
    if (state == WeakPath) {
      weakPathUsed = true // Der Pfad wird unpassierbar nach dem ersten Betreten
    }
  }
}
