package de.htwg.se.blindmaze.model.managers.managersImp

import de.htwg.se.blindmaze.model._
import de.htwg.se.blindmaze.model.managers.IGameManager
import de.htwg.se.blindmaze.utils.Direction
import de.htwg.se.blindmaze.model.managers.GameState
import de.htwg.se.blindmaze.model.grid.IGrid
import de.htwg.se.blindmaze.model.player.IPlayer
import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._

//State Pattern

case class NotStartedState(
    val grid: IGrid,
    val current: Int = 1
  ) 
  extends IGameManager {
  override def startGame: IGameManager = {
    RunningState (grid.createGrid(List(injector.instance[IPlayer](Names.named("1")), injector.instance[IPlayer](Names.named("2")))))
  }
  override def quitGame: IGameManager = this
   
  override def resetGame: IGameManager = this
  
  override def moveNext(direction: Direction): IGameManager = this
  override def showGrid: String = "Game not running"
  override def state: GameState = GameState.NotStarted

  override def invalidCommand: IGameManager = this
}