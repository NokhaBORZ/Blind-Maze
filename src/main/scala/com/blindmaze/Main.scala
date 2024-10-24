package com.blindmaze

import com.blindmaze.model.{Grid, Item, Player}
import com.blindmaze.math.{Direction, Position}

object Main {

  def main(args: Array[String]): Unit = {
    // Erstelle das Spielfeld (Grid) mit einer Größe von 5x5
    val grid = new Grid(5)

    // Erstelle zwei Spieler, die sich auf unterschiedlichen Positionen befinden
    val player1 = new Player(Position(0, 0))
    val player2 = new Player(Position(4, 4))

    // Erstelle ein paar Items
    val sword = new Item("Sword") {
      def applyEffect(player: Player): Unit = {
        println(s"${player} used the $name. It's powerful!")
      }
    }

    val shield = new Item("Shield") {
      def applyEffect(player: Player): Unit = {
        println(s"${player} used the $name. Defense increased!")
      }
    }

    // Platziere die Items auf dem Grid
    grid.placeItem(sword, Position(2, 2))
    grid.placeItem(shield, Position(1, 1))

    // Starte das Spiel, indem der erste Spieler einen Zug macht
    println("Starting game...")

    // Bewege Spieler 1 nach rechts
    grid.movePlayer(player1, Direction.Right)
    grid.movePlayer(player1, Direction.Down)

    // Bewege Spieler 2 nach links
    grid.movePlayer(player2, Direction.Left)
    grid.movePlayer(player2, Direction.Up)

    // Spieler 1 hebt ein Item auf
    grid.tiles(player1.position.x)(player1.position.y).pickUpItem(player1)

    // Spieler 2 hebt ein Item auf
    grid.tiles(player2.position.x)(player2.position.y).pickUpItem(player2)

    // Spieler 1 nutzt ein Item aus seinem Inventar
    player1.useItem(sword)
  }
}
