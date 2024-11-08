package de.htwg.se.blindmaze.model

import de.htwg.se.blindmaze.math.Position

case class Player(id: String, position: Position) {
  def move(position: Position): Player = {
    Player(id, position)
  }
}
