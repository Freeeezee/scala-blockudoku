package blockudoku.views.composed

import blockudoku.services.console.ConsoleStyle.highlighted

case class RegularConsoleElement(content: String, override val isInteractable: Boolean = false) extends ConsoleElement {
  override val size: Int = 1

  override def content(highlightIndex: Int): String = {
    if highlightIndex == size then highlightedContent
    else content
  }

  private def highlightedContent: String = {
    content.split('\n').map(highlighted).mkString("\n")
  }

  override def interactableIndices(currentIndex: Int): List[List[Int]] = {
    if isInteractable then List[List[Int]](List(currentIndex))
    else List[List[Int]]()
  }
}
