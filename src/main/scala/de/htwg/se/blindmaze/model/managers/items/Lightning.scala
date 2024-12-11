package de.htwg.se.blindmaze.model.items

import de.htwg.se.blindmaze.model.managers.GameManager

case class Lightning(name: String, description: String = "") extends Item {
    override def use(gameManager: GameManager): GameManager = ???
    override def rarity: Rarity = Rarity.Common
}