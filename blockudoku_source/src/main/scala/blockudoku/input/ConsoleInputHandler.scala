package blockudoku.input

import blockudoku.services.Event

class ConsoleInputHandler(reader: ConsoleReader) {
  val arrowUpKey: Event = Event()
  val arrowDownKey: Event = Event()
  val arrowLeftKey: Event = Event()
  val arrowRightKey: Event = Event()
  
  val enterKey: Event = Event()
  val qKey: Event = Event()
  
  def run(): Unit = {
    val key = reader.readKey()
    
    if key == 27 && reader.readKey() == 91 then arrowsPressed()
    else if key == 13 then enterPressed()
    else if key == 'q'.toInt then qPressed()
  }
  
  private def arrowsPressed(): Unit = {
    val arrow = reader.readKey()
    arrow match {
      case 65 => arrowUpKey.invoke()
      case 68 => arrowLeftKey.invoke()
      case 66 => arrowDownKey.invoke()
      case 67 => arrowRightKey.invoke()
    }
  }

  private def enterPressed(): Unit = enterKey.invoke()
  
  private def qPressed(): Unit = qKey.invoke()
  
  def close(): Unit = {
    reader.close()
  }
}
