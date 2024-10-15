// PlayerInputHandler.scala
object PlayerInputHandler {

  // Diese Methode verarbeitet die Eingabe für die Bewegung der Spieler
  def getPlayerInput(playerIndex: Int): Option[String] = {
    println(s"Spieler ${playerIndex + 1}, du bist dran! (w/a/s/d für Hoch/Links/Runter/Rechts, 'exit' zum Beenden)")

    // Eingabe lesen
    val input = scala.io.StdIn.readLine().toLowerCase

    input match {
      case "w" | "a" | "s" | "d" => Some(input) // Gültige Bewegungseingaben
      case "exit" =>
        println("Spiel beendet.")
        None // Gibt None zurück, um das Spiel zu beenden
      case _ =>
        println("Ungueltige Eingabe! Bitte 'w', 'a', 's', 'd' oder 'exit' eingeben.")
        getPlayerInput(playerIndex) // Eingabe erneut anfordern
    }
  }
}
