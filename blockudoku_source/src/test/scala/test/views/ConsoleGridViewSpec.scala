package test.views

import blockudoku.controllers.GridController
import blockudoku.views.console.ConsoleGridView
import org.scalatest.Assertion
import test.UnitSpec

class ConsoleGridViewSpec extends UnitSpec {
  object `A ConsoleGridView` {
    object `when size 9x9` {
      def `should display a 9x9 grid`: Assertion = {
        val gridController = GridController(9, 9)
        val gridView = ConsoleGridView(gridController)
        viewContent(gridView).replace("\r\n", "\n") should be(
          """x----x----x----x----x----x----x----x----x----x
            || 00 | 01 | 02 | 03 | 04 | 05 | 06 | 07 | 08 |
            |x----x----x----x----x----x----x----x----x----x
            || 09 | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 |
            |x----x----x----x----x----x----x----x----x----x
            || 18 | 19 | 20 | 21 | 22 | 23 | 24 | 25 | 26 |
            |x----x----x----x----x----x----x----x----x----x
            || 27 | 28 | 29 | 30 | 31 | 32 | 33 | 34 | 35 |
            |x----x----x----x----x----x----x----x----x----x
            || 36 | 37 | 38 | 39 | 40 | 41 | 42 | 43 | 44 |
            |x----x----x----x----x----x----x----x----x----x
            || 45 | 46 | 47 | 48 | 49 | 50 | 51 | 52 | 53 |
            |x----x----x----x----x----x----x----x----x----x
            || 54 | 55 | 56 | 57 | 58 | 59 | 60 | 61 | 62 |
            |x----x----x----x----x----x----x----x----x----x
            || 63 | 64 | 65 | 66 | 67 | 68 | 69 | 70 | 71 |
            |x----x----x----x----x----x----x----x----x----x
            || 72 | 73 | 74 | 75 | 76 | 77 | 78 | 79 | 80 |
            |x----x----x----x----x----x----x----x----x----x
            |""".stripMargin.replace("\r\n", "\n"))
      }
    }
    object `when size 4x4` {
      def `should display a 4x4 grid`: Assertion = {
        val gridController = GridController(4, 4)
        val gridView = ConsoleGridView(gridController)
        viewContent(gridView).replace("\r\n", "\n") should be(
          """x----x----x----x----x
            || 00 | 01 | 02 | 03 |
            |x----x----x----x----x
            || 04 | 05 | 06 | 07 |
            |x----x----x----x----x
            || 08 | 09 | 10 | 11 |
            |x----x----x----x----x
            || 12 | 13 | 14 | 15 |
            |x----x----x----x----x
            |""".stripMargin.replace("\r\n", "\n"))
      }
    }
    object `when size 6x6` {
      def `should display a 6x6 grid`: Assertion = {
        val gridController = GridController(6, 6)
        val gridView = ConsoleGridView(gridController)
        viewContent(gridView).replace("\r\n", "\n") should be(
          """x----x----x----x----x----x----x
            || 00 | 01 | 02 | 03 | 04 | 05 |
            |x----x----x----x----x----x----x
            || 06 | 07 | 08 | 09 | 10 | 11 |
            |x----x----x----x----x----x----x
            || 12 | 13 | 14 | 15 | 16 | 17 |
            |x----x----x----x----x----x----x
            || 18 | 19 | 20 | 21 | 22 | 23 |
            |x----x----x----x----x----x----x
            || 24 | 25 | 26 | 27 | 28 | 29 |
            |x----x----x----x----x----x----x
            || 30 | 31 | 32 | 33 | 34 | 35 |
            |x----x----x----x----x----x----x
            |""".stripMargin.replace("\r\n", "\n"))
      }
    }
  }
}
