package blockudoku.views.composed

trait Frame(elements: List[ConsoleElement]) extends ConsoleElement {
  override val size: Int = calculateSize

  private def calculateSize: Int = {
    var size = 1

    elements.foreach { element =>
      size = size + element.size
    }

    size
  }
}