package de.htwg.se.blindmaze.utils

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar

class ObservableSpec extends AnyWordSpec with Matchers with MockitoSugar {

    "An Observable" should {

        "add an observer" in {
            val observable = new Observable
            val observer = mock[Observer]

            observable.add(observer)
            observable.subscribers should contain(observer)
        }

        "remove an observer" in {
            val observable = new Observable
            val observer = mock[Observer]

            observable.add(observer)
            observable.remove(observer)
            observable.subscribers should not contain observer
        }

        "notify all observers" in {
            val observable = new Observable
            val observer1 = mock[Observer]
            val observer2 = mock[Observer]
            val event = GameEvent.OnGameStartEvent

            observable.add(observer1)
            observable.add(observer2)
            observable.notifyObservers(event)

            verify(observer1).update(event)
            verify(observer2).update(event)
        }

        "not notify removed observers" in {
            val observable = new Observable
            val observer = mock[Observer]
            val event = GameEvent.OnGameStartEvent

            observable.add(observer)
            observable.remove(observer)
            observable.notifyObservers(event)

            verify(observer, never()).update(event)
        }
    }
}