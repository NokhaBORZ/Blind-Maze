package de.htwg.se.blindmaze.model.item.ItemsImp

import com.google.inject.Inject
import com.google.inject.name.Names
import com.google.inject.Guice
import net.codingwell.scalaguice.InjectorExtensions._

import de.htwg.se.blindmaze.model.managers.IGameManager
import de.htwg.se.blindmaze.model.managers.managersImp.RunningState
import de.htwg.se.blindmaze.model.item.{IItem, Rarity}
import de.htwg.se.blindmaze.model.player.IPlayer
import de.htwg.se.blindmaze.modules.AppModule

/**
 * Lantern item that reveals walls in a 3x3 radius around the current player.
 */
case class Lantern @Inject()(name: String) extends IItem {
  private val revealRadius = 1 // 1 = 3x3 area (from -1 to +1 in each direction)
  
  override def use(gameManager: IGameManager): IGameManager = {
    gameManager match {
      case running: RunningState =>
        val injector = Guice.createInjector(new AppModule)
        val currentPlayer = injector.instance[IPlayer](Names.named(running.current.toString))
        
        running.grid.getPlayer(currentPlayer) match {
          case Some(playerPosition) =>
            val revealedGrid = running.grid.revealWallsAround(playerPosition, revealRadius)
            running.copy(grid = revealedGrid)
          case None =>
            gameManager // Player not found, return unchanged
        }
      case _ => gameManager // Not in running state, return unchanged
    }
  }
  
  override def rarity: Rarity = Rarity.Common
  
  val description: String = "A lantern that reveals walls in a 3x3 area around you"
}