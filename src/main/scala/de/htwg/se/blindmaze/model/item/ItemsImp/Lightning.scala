package de.htwg.se.blindmaze.model.item.ItemsImp

import de.htwg.se.blindmaze.model.managers.IGameManager
import de.htwg.se.blindmaze.model.managers.managersImp.RunningState
import de.htwg.se.blindmaze.model.item.{IItem, Rarity}

/**
 * Lightning item that reveals ALL walls on the grid.
 * A powerful rare item for strategic advantage.
 */
case class Lightning(name: String, description: String = "A powerful flash that reveals all walls in the maze") extends IItem {
  
  override def use(gameManager: IGameManager): IGameManager = {
    gameManager match {
      case running: RunningState =>
        val revealedGrid = running.grid.showAllWalls()
        running.copy(grid = revealedGrid)
      case _ => gameManager // Not in running state, return unchanged
    }
  }
  
  override def rarity: Rarity = Rarity.Rare
}