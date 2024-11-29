package de.htwg.se.blindmaze.model.items

import de.htwg.se.blindmaze.model.GameManager

case class Lantern(name: String, description: String = "") extends Item {
    override def use(gameManager: GameManager): GameManager = ???
    override def rarity: Rarity = Rarity.Common
}