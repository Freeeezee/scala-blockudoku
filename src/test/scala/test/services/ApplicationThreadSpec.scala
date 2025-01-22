package test.services

import blockudoku.services.ApplicationThread
import test.UnitSpec

import java.util.concurrent.CancellationException
import scala.io.StdIn.readLine

class ApplicationThreadSpec extends UnitSpec {
  "An application thread" should {
    "run a code block" in {
      var x = 0

      val thread = ApplicationThread().run {
        x = 1
      }

      thread.await()

      x should be (1)
      thread.isCancelled should be (false)
      thread.isCompleted should be (true)
    }
    "exit when a task throws a cancellation exception" in {
      var x = 0

      val thread = ApplicationThread().run {
        x = 1
        throw new CancellationException("Task was cancelled")
        x = 2
      }

      thread.await()

      x should be (1)
      thread.isCancelled should be (true)
      thread.isCompleted should be (true)
    }
    "exit when a task throws any exception" in {
      var x = 0

      val thread = ApplicationThread().run {
        x = 1
        throw new Exception("Task failed")
        x = 2
      }

      thread.await()

      x should be (1)
      thread.isCancelled should be (true)
      thread.isCompleted should be(true)
    }
    "be cancellable" in {
      val thread = ApplicationThread().run {
        readLine()
      }

      thread.cancel()

      thread.isCancelled should be (true)
    }
  }
}
