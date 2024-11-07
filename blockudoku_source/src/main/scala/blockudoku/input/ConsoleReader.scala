package blockudoku.input

import org.jline.terminal.TerminalBuilder

class ConsoleReader {
  private val terminal = TerminalBuilder.builder().system(true).jansi(true).build()
  terminal.enterRawMode()
  private val reader = terminal.reader()

  def readKey(): Int = {
    reader.read()
  }

  def close(): Unit = {
    reader.close()
    terminal.close()
  }
}
