package test.input

import blockudoku.input.ConsoleInputHandler
import test.UnitSpec

class ConsoleInputHandlerSpec extends UnitSpec {
  "A ConsoleInputHandler" should {
    "correctly handle key input" in {
      val input = List[Int](
        27, 91, 65, // Arrow up
        27, 91, 66, // Arrow down
        27, 91, 68, // Arrow left
        27, 91, 67, // Arrow right
        13, // Enter
        'q'.toInt // Q
      )

      val reader = new ConsoleReaderMock(input)
      val handler = new ConsoleInputHandler(reader)

      var verification = 0

      handler.arrowUpKey.addListener(() => verification += 1)
      handler.arrowDownKey.addListener(() => verification += 1)
      handler.arrowLeftKey.addListener(() => verification += 1)
      handler.arrowRightKey.addListener(() => verification += 1)
      handler.enterKey.addListener(() => verification += 1)
      handler.qKey.addListener(() => verification += 1)

      for (_ <- 1 to 6)
        handler.run()

      verification should be(6)
    }
  }
}
