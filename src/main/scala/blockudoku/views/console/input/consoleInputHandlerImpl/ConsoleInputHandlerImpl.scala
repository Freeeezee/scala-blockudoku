package blockudoku.views.console.input.consoleInputHandlerImpl

import blockudoku.services.Event
import blockudoku.views.console.input.ConsoleInputHandler
import org.jline.keymap.KeyMap

class ConsoleInputHandlerImpl extends ConsoleInputHandler {
  private val keyMap = initializeKeymap()
  private val reader = ConsoleReader(keyMap)

  def run(): Unit = {
    reader.readAndExecute()
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
    keyMap.bind(() => zKey.invoke(), "z")
    keyMap.bind(() => rKey.invoke(), "r")
    keyMap
  }
}
