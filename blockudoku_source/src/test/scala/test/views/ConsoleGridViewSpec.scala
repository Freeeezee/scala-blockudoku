package test.views
import test.UnitSpec
import blockudoku.controllers.GridController
import blockudoku.views.console.ConsoleGridView

class ConsoleGridViewSpec extends UnitSpec {
  "GridView" should {
    "display a 9x9 grid" in {
      val gridController = GridController()
      val gridView = ConsoleGridView(gridController)
      val str = new StringBuilder()
      str.append("""x----x----x----x----x----x----x----x----x----x
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
                   |""".stripMargin)
      //str.append("")
      viewContent(gridView) should be (str.result())
     }
  }
}
