package blockudoku

/**
 * Needs to be implemented by classes that want to listen to changes in an observable.
 */
trait Observer {
  def update() : Unit
}

/**
 * Needs to be implemented by classes that want to notify observers about changes.
 */
trait Observable {

  private var observers: List[Observer] = List()

  /**
   * Adds an observer to the list of observers.
   * @param observer The observer to add.
   */
  def addObserver(observer: Observer): Unit = {
    observers = observer :: observers
  }

  /**
   * Invokes the update method of all observers.
   */
  def notifyObservers(): Unit = {
    observers.foreach(o => o.update())
  }
}