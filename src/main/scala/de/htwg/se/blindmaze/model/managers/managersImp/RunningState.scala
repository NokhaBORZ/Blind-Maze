package de.htwg.se.blindmaze.model.managers.managersImp

import de.htwg.se.blindmaze.model._
import de.htwg.se.blindmaze.model.managers.IGameManager
import de.htwg.se.blindmaze.utils.Direction
import de.htwg.se.blindmaze.model.managers.GameState
import de.htwg.se.blindmaze.model.grid.IGrid
import de.htwg.se.blindmaze.model.player.IPlayer
import de.htwg.se.blindmaze.model.item.IItem
import de.htwg.se.blindmaze.model.tiles.{Tile, TileContent}
import de.htwg.se.blindmaze.utils.AudioManager
import com.google.inject.Injector
import com.google.inject.name.Names
import com.google.inject.Guice
import net.codingwell.scalaguice.InjectorExtensions._

case class RunningState(
    val grid: IGrid,
    val current: Int = 1,
    val playerInventories: Map[Int, List[IItem]] = Map(1 -> List(), 2 -> List())
) extends IGameManager {
  override def startGame: IGameManager = this

  override def quitGame: IGameManager = {
    println("Quitting the game...")
    NotStartedState(injector.instance[IGrid])
  }

  override def resetGame: IGameManager = {
    copy(
      grid = grid.createGrid(List(
        injector.instance[IPlayer](Names.named("1")), 
        injector.instance[IPlayer](Names.named("2"))
      )),
      current = current,
      playerInventories = Map(1 -> List(), 2 -> List())
    )
  }

  override def moveNext(direction: Direction, playerId: Int): IGameManager = {
    if (current != playerId) {
      return this
    }

    // Logic for moving player
    if (!grid.canMove(current, direction)) {
      AudioManager.playSound("collision")
      return this
    }

    // Get current player position before move
    val player = injector.instance[IPlayer](Names.named(current.toString))
    val currentPosition = grid.getPlayer(player).getOrElse(return this)
    val targetPosition = currentPosition.move(direction)
    
    // Check what's at the target position
    val targetTile = grid.get(targetPosition)
    var updatedInventories = playerInventories
    var updatedGrid = grid

    // Handle chest pickup
    targetTile.content match {
      case TileContent.ChestTile(chest) =>
        // Add item to player's inventory
        val currentInventory = playerInventories.getOrElse(current, List())
        updatedInventories = playerInventories.updated(current, chest.item :: currentInventory)
        println(s"Player $current picked up: ${chest.item.name}")
        // Clear the chest tile before moving (player will move onto empty tile)
        updatedGrid = grid.set(targetPosition, Tile(TileContent.Empty))
      case _ => // No special handling needed
    }

    // Move the player on the updated grid
    val newGrid = updatedGrid.movePlayer(current, direction)
    AudioManager.playSound("move")

    // Check if the player reaches the VictoryTile after moving
    val playerPosition = newGrid.getPlayer(player)
    println(s"Player $current moved to $playerPosition")

    playerPosition match {
      case Some(position) if grid.get(position).content == TileContent.Victory =>
        println(s"Player $current wins!")
        AudioManager.playSound("victory")
        return FinishedState(newGrid.showAllWalls(), current)
        
      case _ => // Continue if no victory
    }

    val newCurrent = if (current == 1) 2 else 1
    RunningState(newGrid, newCurrent, updatedInventories)
  }

  /**
   * Use an item from the current player's inventory.
   * @return Updated game state with item effect applied
   */
  def useItem(): IGameManager = {
    val currentInventory = playerInventories.getOrElse(current, List())
    
    currentInventory match {
      case item :: rest =>
        println(s"Player $current used: ${item.name}")
        val newState = item.use(this)
        newState match {
          case running: RunningState =>
            running.copy(playerInventories = playerInventories.updated(current, rest))
          case other => other
        }
      case Nil =>
        println(s"Player $current has no items to use")
        this
    }
  }

  override def showGrid: String = {
    grid.showGrid()
  }

  override def state: GameState = GameState.Running

  override def invalidCommand: IGameManager = this

  override def changeCurrent: IGameManager = RunningState(grid, if (current == 1) 2 else 1, playerInventories)
}
