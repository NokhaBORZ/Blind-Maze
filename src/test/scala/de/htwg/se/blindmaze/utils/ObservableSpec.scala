package de.htwg.se.blindmaze.utils

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ObservableSpec extends AnyWordSpec with Matchers {

    "An Observable" should {

        "add an observer" in {
            val observable = new Observable
            val observer = new Observer {
                override def update(): Unit = {}
            }
            observable.add(observer)
            observable.subscribers should contain(observer)
        }

        "remove an observer" in {
            val observable = new Observable
            val observer = new Observer {
                override def update(): Unit = {}
            }
            observable.add(observer)
            observable.remove(observer)
            observable.subscribers should not contain observer
        }

        "notify all observers" in {
            val observable = new Observable
            var updated = false
            val observer = new Observer {
                override def update(): Unit = updated = true
            }
            observable.add(observer)
            observable.notifyObservers()
            updated should be(true)
        }

        "not notify removed observers" in {
            val observable = new Observable
            var updated = false
            val observer = new Observer {
                override def update(): Unit = updated = true
            }
            observable.add(observer)
            observable.remove(observer)
            observable.notifyObservers()
            updated should be(false)
        }

        "notify multiple observers" in {
            val observable = new Observable
            var updated1 = false
            var updated2 = false
            val observer1 = new Observer {
                override def update(): Unit = updated1 = true
            }
            val observer2 = new Observer {
                override def update(): Unit = updated2 = true
            }
            observable.add(observer1)
            observable.add(observer2)
            observable.notifyObservers()
            updated1 should be(true)
            updated2 should be(true)
        }
    }
}