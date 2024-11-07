package blockudoku.input

trait ConsoleReader {
  def readKey(): Int
  def close(): Unit
}
