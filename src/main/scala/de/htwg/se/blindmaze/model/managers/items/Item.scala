// Item.scala
package de.htwg.se.blindmaze.model.items

import de.htwg.se.blindmaze.model.managers.GameManager

/**
 * Enumeration representing the rarity of an item.
 */
enum Rarity:
  case Common, Rare, Epic, Legendary

/**
 * Trait representing a generic item in the game.
 */
trait Item {
  /**
   * The name of the item.
   */
  val name: String

  /**
   * Method to use the item, which modifies the game state.
   * 
   * @param gameManager The current game manager instance.
   * @return The updated game manager instance after using the item.
   */
  def use(gameManager: GameManager): GameManager

  /**
   * The rarity of the item.
   */
  def rarity: Rarity

  /**
   * A description of the item.
   */
  def description: String
}
