package de.htwg.se.blindmaze.model.managers.managersImp

import de.htwg.se.blindmaze.model._
import de.htwg.se.blindmaze.model.managers.IGameManager
import de.htwg.se.blindmaze.utils.Direction
import de.htwg.se.blindmaze.model.managers.GameState
import de.htwg.se.blindmaze.model.grid.IGrid
import de.htwg.se.blindmaze.model.player.IPlayer
import com.google.inject.Injector
import com.google.inject.name.Names
import com.google.inject.Guice
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.blindmaze.model.tiles.TileContent

case class RunningState(
    val grid: IGrid,
    val current: Int = 1
) extends IGameManager {
  override def startGame: IGameManager = this

  override def quitGame: IGameManager = {
    println("Quitting the game...")
    NotStartedState(injector.instance[IGrid])
  }

  override def resetGame: IGameManager = {
    copy(grid.createGrid(List(injector.instance[IPlayer](Names.named("1")), injector.instance[IPlayer](Names.named("2")))), current)
  }

  override def moveNext(direction: Direction): IGameManager = {
    // Logic for moving player
    if (!grid.canMove(current, direction)) {
        return this
    }

    val newGrid = grid.movePlayer(current, direction)

    // Check if the player reaches the VictoryTile after moving
    val player = injector.instance[IPlayer](Names.named(current.toString))
    val playerPosition = newGrid.getPlayer(player)
    println(s"Player $current moved to $playerPosition")

    playerPosition match {
        case Some(position) if grid.get(position).content == TileContent.Victory =>
            println(s"Player $current wins!")
            return FinishedState(newGrid, current)
        case _ => // Continue if no victory
    }

    val newCurrent = if (current == 1) 2 else 1
    RunningState(newGrid, newCurrent) // Ensure the player switch occurs correctly
}

  override def showGrid: String = {
    grid.showGrid()
  }

  override def state: GameState = GameState.Running

  override def invalidCommand: IGameManager = this

  override def changeCurrent: IGameManager = RunningState(grid, if (current == 1) 2 else 1)
}

