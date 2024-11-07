package test.input

import blockudoku.input.ConsoleReader

class ConsoleReaderMock(keys: List[Int]) extends ConsoleReader {
  private var index: Int = 0
  
  override def readKey(): Int = {
    if (index < keys.length) {
      val key = keys(index)
      index += 1
      key
    } else {
      throw new IllegalStateException("No more keys to read")
    }
  }

  override def close(): Unit = {}
}
