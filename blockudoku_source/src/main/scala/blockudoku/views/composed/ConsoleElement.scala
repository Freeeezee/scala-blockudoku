package blockudoku.views.composed

trait ConsoleElement {
  val isInteractable: Boolean = false
  val size: Int

  def content(highlightIndex: Int): String

  def interactableIndices(currentIndex: Int = 1): List[List[Int]]
}
