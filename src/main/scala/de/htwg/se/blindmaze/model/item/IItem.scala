package de.htwg.se.blindmaze.model.item

import de.htwg.se.blindmaze.model.managers.IGameManager

/**
 * Enumeration representing the rarity of an item.
 */
enum Rarity:
  case Common, Rare, Epic, Legendary

/**
 * Trait representing a generic item in the game.
 */
trait IItem {
  /**
   * The name of the item.
   */
  val name: String

  /**
   * Method to use the item, which modifies the game state.
   * 
   * @param IGameManager The current game manager instance.
   * @return The updated game manager instance after using the item.
   */
  def use(IGameManager: IGameManager): IGameManager

  /**
   * The rarity of the item.
   */
  def rarity: Rarity

  /**
   * A description of the item.
   */
  def description: String
}
