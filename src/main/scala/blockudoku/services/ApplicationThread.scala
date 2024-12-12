package blockudoku.services

import java.util.concurrent.Executors
import scala.concurrent.{ExecutionContext, Future}

class ApplicationThread {
  protected implicit val context: ExecutionContext =
    ExecutionContext.fromExecutorService(Executors.newSingleThreadExecutor())

  def run(code: => Unit): Unit = Future(code)
}
