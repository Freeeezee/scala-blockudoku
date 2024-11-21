package test.services

import blockudoku.services.Event
import test.UnitSpec

class EventSpec extends UnitSpec {
  "An Event" when {
    "a listener is added" should {
      "notify the listener" in {
        val event = Event()
        var notified = false
        event.addListener { () => notified = true }
        event.invoke()

        notified should be(true)
      }
    }
  }
}
