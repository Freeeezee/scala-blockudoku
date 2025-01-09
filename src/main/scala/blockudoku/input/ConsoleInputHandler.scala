package blockudoku.input

import blockudoku.services.Event

trait ConsoleInputHandler {
  val arrowUpKey: Event = Event()
  val arrowDownKey: Event = Event()
  val arrowLeftKey: Event = Event()
  val arrowRightKey: Event = Event()

  val enterKey: Event = Event()
  val qKey: Event = Event()
  val zKey: Event = Event()
  val rKey: Event = Event()
  
  def run(): Unit
}