package blockudoku.observer

trait Observer {
  def update() : Unit
}

trait Observable {

  private var observers: List[Observer] = List()

  def addObserver(observer: Observer): Unit = {
    observers = observer :: observers
  }

  def notifyObservers(): Unit = {
    observers.foreach(o => o.update())
  }
}