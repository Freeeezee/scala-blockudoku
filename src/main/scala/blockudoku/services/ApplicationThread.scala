package blockudoku.services

import java.util.concurrent.{CancellationException, Executors}
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future, Promise}

/**
 * Allows code to be run in a separate thread.
 */
class ApplicationThread {
  private val executor = Executors.newSingleThreadExecutor()
  protected implicit val context: ExecutionContext = ExecutionContext.fromExecutorService(executor)

  /**
   * Runs the given code in a separate thread.
   * @param code The code to run.
   * @return A task that can be cancelled.
   */
  def run(code: => Unit): CancelableTask = {
    val promise = Promise[Unit]()

    val future = Future {
      try {
        code
        promise.success(())
      } catch {
        case e: CancellationException =>
          promise.tryFailure(e)
          promise
        case t: Throwable =>
          promise.tryFailure(t)
          promise
      }
    }

    new CancelableTask(future, promise, executor)
  }
}

/**
 * A task that can be cancelled. This is a wrapper around a future and a promise. Should not be created directly.
 * @param future The future that represents the task.
 * @param promise The promise that the task is based on.
 * @param executor The executor that runs the task.
 */
class CancelableTask(
                      val future: Future[Promise[Unit]],
                      private val promise: Promise[Unit],
                      private val executor: java.util.concurrent.ExecutorService
                    ) {
  /**
   * Cancels the task.
   * @return True if the task was cancelled, false otherwise.
   */
  def cancel(): Boolean = {
    val cancelResult = executor.shutdownNow()
    promise.tryFailure(new CancellationException("Task was cancelled"))

    !cancelResult.isEmpty
  }

  /**
   * Waits for the task to complete.
   */
  def await(): Unit = {
    Await.result(future, Duration.Inf)
  }

  /**
   * Checks if the task was cancelled.
   * @return True if the task was cancelled, false otherwise.
   */
  def isCancelled: Boolean = promise.future.value.exists(_.isFailure)

  /**
   * Checks if the task is completed.
   * @return True if the task is completed, false otherwise.
   */
  def isCompleted: Boolean = future.isCompleted
}
