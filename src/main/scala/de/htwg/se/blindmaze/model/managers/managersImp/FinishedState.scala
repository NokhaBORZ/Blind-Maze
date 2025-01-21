package de.htwg.se.blindmaze.model.managers.managersImp

import de.htwg.se.blindmaze.model._
import de.htwg.se.blindmaze.model.managers.IGameManager
import de.htwg.se.blindmaze.utils.Direction
import de.htwg.se.blindmaze.model.managers.GameState
import de.htwg.se.blindmaze.model.grid.IGrid
import de.htwg.se.blindmaze.model.player.IPlayer
import com.google.inject.Injector
import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._


case class FinishedState(
    val grid: IGrid,
    val winner: Int
) extends IGameManager {
  override def startGame: IGameManager = this
  override def quitGame: IGameManager = NotStartedState(injector.instance[IGrid])
  override def resetGame: IGameManager = RunningState(grid.createGrid(List(injector.instance[IPlayer](Names.named("1")), injector.instance[IPlayer](Names.named("2")))))
  override def moveNext(direction: Direction, playerId: Int): IGameManager = this
  override def showGrid: String = grid.showGrid()
  override def state: GameState = GameState.Finished
  override def invalidCommand: IGameManager = this
  override val current: Int = winner

  def getWinnerMessage: String = s"Player $winner has won the game!"
}