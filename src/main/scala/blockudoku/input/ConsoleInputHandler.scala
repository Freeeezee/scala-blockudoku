package blockudoku.input

import blockudoku.services.Event
import org.jline.keymap.{BindingReader, KeyMap}
import org.jline.terminal.TerminalBuilder

class ConsoleInputHandler {
  val arrowUpKey: Event = Event()
  val arrowDownKey: Event = Event()
  val arrowLeftKey: Event = Event()
  val arrowRightKey: Event = Event()
  
  val enterKey: Event = Event()
  val qKey: Event = Event()

  private val terminal = TerminalBuilder.builder().system(true).build()
  terminal.enterRawMode()
  private val reader = BindingReader(terminal.reader())

  private val keyMap = initializeKeymap()

  def run(): Unit = {
    Option(reader.readBinding(keyMap)) match {
      case Some(binding) => binding.apply()
      case None =>
    }
  }

  private def enterPressed(): Unit = enterKey.invoke()
  
  private def qPressed(): Unit = qKey.invoke()

  private def initializeKeymap(): KeyMap[() => Unit] = {
    val keyMap = new KeyMap[() => Unit]()
    keyMap.bind(() => arrowUpKey.invoke(), "\u001B[A")
    keyMap.bind(() => arrowDownKey.invoke(), "\u001B[B")
    keyMap.bind(() => arrowLeftKey.invoke(), "\u001B[D")
    keyMap.bind(() => arrowRightKey.invoke(), "\u001B[C")
    keyMap.bind(() => enterKey.invoke(), "\r")
    keyMap.bind(() => qKey.invoke(), "q")
    keyMap.bind(() => arrowUpKey.invoke(), "\u001bOA")
    keyMap.bind(() => arrowDownKey.invoke(), "\u001bOB")
    keyMap.bind(() => arrowLeftKey.invoke(), "\u001bOD")
    keyMap.bind(() => arrowRightKey.invoke(), "\u001BOC")
    keyMap
  }
}
