package blockudoku.windows

trait Window {
  def display() : Unit
  def anyChange(): Boolean
  def handleInput(): Unit
}
