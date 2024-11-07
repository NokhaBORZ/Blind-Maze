package de.htwg.se.blindmaze.utils

// Hilfsfunktionen können hier definiert werden
object Helper {

  // Beispiel: Funktion zur Berechnung der Entfernung zwischen zwei Positionen
  def calculateDistance(pos1: (Int, Int), pos2: (Int, Int)): Double = {
    val (x1, y1) = pos1
    val (x2, y2) = pos2
    Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2))
  }
}
