package blockudoku.services

import java.util.concurrent.{CancellationException, Executors}
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future, Promise}

class ApplicationThread {
  private val executor = Executors.newSingleThreadExecutor()
  protected implicit val context: ExecutionContext = ExecutionContext.fromExecutorService(executor)

  def run(code: => Unit): CancelableTask = {
    val promise = Promise[Unit]()

    val future = Future {
      try {
        code
        promise.success(())
      } catch {
        case e: CancellationException =>
          promise.failure(e)
        case t: Throwable =>
          promise.failure(t)
      }
    }

    new CancelableTask(future, promise, executor)
  }
}

class CancelableTask(
                      val future: Future[Promise[Unit]],
                      private val promise: Promise[Unit],
                      private val executor: java.util.concurrent.ExecutorService
                    ) {
  def cancel(): Boolean = {
    // Attempt to cancel the running task
    val cancelResult = executor.shutdownNow()
    promise.tryFailure(new CancellationException("Task was cancelled"))

    // Return true if the task was successfully cancelled
    !cancelResult.isEmpty
  }
  def await(): Unit = {
    Await.result(future, Duration.Inf)
  }

  def isCancelled: Boolean = promise.future.value.exists(_.isFailure)

  def isCompleted: Boolean = future.isCompleted
}
