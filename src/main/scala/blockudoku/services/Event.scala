package blockudoku.services

/**
 * Represents an event that can be listened to.
 */
class Event {
  private var listeners: List[() => Unit] = List()

  /**
   * Invokes all listeners.
   */
  def invoke(): Unit = {
    listeners.foreach(_.apply())
  }
  
  /**
   * Adds a listener to the event.
   * @param listener The listener to add.
   */
  def addListener(listener: () => Unit): Unit = {
     listeners = listener :: listeners
  }
}
