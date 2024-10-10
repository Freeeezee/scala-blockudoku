package blockudoku

trait ObservableObject[P]{
        this:P=>
private var observers:List[P=>Unit]=Nil
        def addObserver(observer:P=>Unit):Unit=observers=observer::observers
        def notifyObservers():Unit=observers.foreach(_.apply(this))
        }
