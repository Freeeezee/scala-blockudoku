package blockudoku.views.console.composed

trait ConsoleElement {
  val isInteractable: Boolean = false
  val size: Int
  
  val onSelect: () => Unit = () => ()

  def content(highlightIndex: Int): String

  def interactableIndices(currentIndex: Int = 1): List[List[Int]]
  
  def get(index: Int): ConsoleElement
}
