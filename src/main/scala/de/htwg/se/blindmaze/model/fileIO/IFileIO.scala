package de.htwg.se.blindmaze.model.fileIO

import de.htwg.se.blindmaze.model.managers.IGameManager

trait IFileIO {
  def load: IGameManager
  def save(gameManager: IGameManager): Unit
}