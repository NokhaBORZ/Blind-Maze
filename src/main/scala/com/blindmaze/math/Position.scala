package com.blindmaze.math

// Klasse für Position auf dem Spielfeld
case class Position(x: Int, y: Int) {

    // Methode zum Bewegen der Position
    def move(direction: Direction.Direction): Position = {
        direction match {
        case Direction.Up => Position(x, y - 1)
        case Direction.Down => Position(x, y + 1)
        case Direction.Left => Position(x - 1, y)
        case Direction.Right => Position(x + 1, y)
        case _ => this
        }
    }
}
