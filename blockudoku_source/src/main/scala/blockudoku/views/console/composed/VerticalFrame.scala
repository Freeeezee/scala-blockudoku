package blockudoku.views.console.composed

import blockudoku.services.console.ConsoleStyle.highlighted

case class VerticalFrame(elements: List[ConsoleElement])(spacing: Int, override val isInteractable: Boolean = false) extends Frame(elements) {
  override def content(highlightIndex: Int): String = {
    val str = new StringBuilder()

    val highlightedContents = contents(highlightIndex)

    for i <- highlightedContents.indices do
      str.append(highlightedContents(i))
      str.append("\n")
      if i < highlightedContents.length - 1 then str.append(space)

    if highlightIndex == size then highlighted(str.result())
    else str.result()
  }

  private def contents(highlightIndex: Int): List[String] = {
    var contents = List[String]()
    var traversedSize: Int = 0

    for i <- elements.indices do
      val content = elements(i).content(highlightIndex - traversedSize)
      contents = contents :+ content
      traversedSize += elements(i).size

    contents
  }

  private def space : String = {
    val str = new StringBuilder()

    for i <- 0 until spacing do
      str.append("\n")

    str.result()
  }

  override def interactableIndices(currentIndex: Int = 1): List[List[Int]] = {
    if !isInteractable then return List[List[Int]]()

    var interactableIndices = List[List[Int]]()

    var index = currentIndex

    elements.foreach { element =>
      element.interactableIndices(index).foreach { row =>
        interactableIndices = interactableIndices :+ row
      }

      index = currentIndex + element.size
    }

    interactableIndices
  }
}

