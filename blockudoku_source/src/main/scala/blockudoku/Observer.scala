package blockudoku

trait Observer[P] {
  def receiveUpdate(): Unit
}
