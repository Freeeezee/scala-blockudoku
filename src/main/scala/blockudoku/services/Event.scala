package blockudoku.services

class Event {
  private var listeners: List[() => Unit] = List()
  
  def invoke(): Unit = {
    listeners.foreach(_.apply())
  }
  
  def addListener(listener: () => Unit): Unit = {
     listeners = listener :: listeners
  }
}
