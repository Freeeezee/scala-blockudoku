package blockudoku.input

import org.jline.terminal.TerminalBuilder
import org.jline.keymap.{BindingReader, KeyMap}

class ConsoleReader(keyMap: KeyMap[() => Unit]) {
  private val terminal = TerminalBuilder.builder().system(true).build()
  terminal.enterRawMode()
  private val reader = BindingReader(terminal.reader())
  
  def readAndExecute(): Unit = {
    Option(reader.readBinding(keyMap)) match {
      case Some(binding) => binding.apply()
      case None =>
    }
  }
}
