package de.htwg.se.blindmaze.model

sealed trait Direction
//Singleton Pattern
object Direction {
  case object Up extends Direction
  case object Down extends Direction
  case object Left extends Direction
  case object Right extends Direction
  
  def opposite(direction: Direction): Direction = direction match {
    case Up => Down
    case Down => Up
    case Left => Right
    case Right => Left
  }
}

