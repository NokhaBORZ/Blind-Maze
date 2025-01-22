package de.htwg.se.blindmaze.model.item

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import play.api.libs.json.Json
import de.htwg.se.blindmaze.model.item.ItemsImp._
import de.htwg.se.blindmaze.model.managers.IGameManager

class ItemsSpec extends AnyWordSpec {

    "A Lantern" should {
        "have the correct default description" in {
            val lantern = Lantern("MyLantern")
            lantern.description shouldBe "A lantern that can light up the maze"
        }
        "have the correct rarity" in {
            val lantern = Lantern("MyLantern")
            lantern.rarity shouldBe Rarity.Common
        }
    }

    "A Lightning" should {
        "be Common rarity by default" in {
            val lightning = Lightning("ShockingStrike")
            lightning.rarity shouldBe Rarity.Common
        }
        "allow custom descriptions" in {
            val lightning = Lightning("Zap", "An electric blast")
            lightning.description shouldBe "An electric blast"
        }
    }

    "A CursedMap" should {
        "be Common rarity by default" in {
            val cursedMap = CursedMap("HiddenPassages")
            cursedMap.rarity shouldBe Rarity.Common
        }
    }

    "An Inventory" should {
        "use method should return IGameManager" in {
            val inv = Inventory("AdventurePack")
            // Just checking if it compiles:
            "inv.use(org.mockito.Mockito.mock(classOf[IGameManager]))" should compile
        }
        "contain newly added items" in {
            val inv = Inventory("MyStuff")
            val lantern = Lantern("Lantern")
            inv.addItem(lantern)
            inv.items should contain(lantern)
        }
        "remove items properly" in {
            val inv = Inventory("RemoveTest")
            val lightning = Lightning("Lightning")
            inv.addItem(lightning)
            inv.removeItem(lightning)
            inv.items should not contain lightning
        }
    }
}