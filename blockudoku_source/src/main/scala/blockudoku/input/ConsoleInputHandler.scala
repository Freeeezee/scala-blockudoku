package blockudoku.input

import blockudoku.App
import blockudoku.views.console.composed.Direction
import blockudoku.views.console.composed.Direction.{Up, Down, Left, Right}
import blockudoku.windows.ConsoleWindow

class ConsoleInputHandler(consoleWindow: ConsoleWindow) {
  private val reader = new ConsoleReader()
  
  def run(): Unit = {
    val key = reader.readKey()
    
    if key == 27 && reader.readKey() == 91 then arrows()
    else if key == 'q'.toInt then exit()
  }
  
  private def arrows(): Unit = {
    val arrow = reader.readKey()
    arrow match {
      case 65 => consoleWindow.navigate(Up)
      case 68 => consoleWindow.navigate(Left)
      case 66 => consoleWindow.navigate(Down)
      case 67 => consoleWindow.navigate(Right)
    }
  }
  
  private def exit(): Unit = App.exit()
  
  def close(): Unit = {
    reader.close()
  }
}
