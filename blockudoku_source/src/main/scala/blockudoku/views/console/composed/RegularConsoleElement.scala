package blockudoku.views.console.composed

import blockudoku.services.console.ConsoleStyle.highlighted

case class RegularConsoleElement(content: String, override val isInteractable: Boolean = false, 
                                 override val onSelect: () => Unit = () => ()) extends ConsoleElement {
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

  override def get(index: Int): ConsoleElement = {
    if index == size then this 
    else throw new IndexOutOfBoundsException
  }
}
