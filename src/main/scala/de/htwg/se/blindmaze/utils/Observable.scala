package de.htwg.se.blindmaze.utils

trait Observer {
  def update(event: GameEvent): Unit
}

class Observable {
  var subscribers: Vector[Observer] = Vector()

  def add(s: Observer): Unit = subscribers = subscribers :+ s

  def remove(s: Observer): Unit = subscribers = subscribers.filterNot(o => o == s)

  def notifyObservers(event: GameEvent): Unit = subscribers.foreach(o => o.update(event: GameEvent))
}