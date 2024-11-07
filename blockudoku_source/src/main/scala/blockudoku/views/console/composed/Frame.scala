package blockudoku.views.console.composed

import scala.util.control.Breaks.{break, breakable}

trait Frame(elements: List[ConsoleElement]) extends ConsoleElement {
  override val size: Int = calculateSize

  private def calculateSize: Int = {
    var size = 1

    elements.foreach { element =>
      size = size + element.size
    }

    size
  }

  override def get(index: Int): ConsoleElement = {
    var traversedSize: Int = 0

    var result: ConsoleElement = null
    breakable {
      for i <- elements.indices do
        if index <= elements(i).size + traversedSize then
          result = elements(i).get(index - traversedSize)
          break
        else traversedSize += elements(i).size
    }

    if result == null then throw new IndexOutOfBoundsException(s"Index $index out of bounds")
    else result
  }
}