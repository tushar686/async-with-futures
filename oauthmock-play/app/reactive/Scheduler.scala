

package reactive

import scala.concurrent.duration.Duration
import scala.concurrent.Future
import scala.concurrent.Promise
import scala.util.Try
import config.ConfigNonBlocking.executionContextSch

object Scheduler {

  def asFuture[T](delay: Duration)(block: => T): Future[T] = {
    val promise = Promise[T]
    
    val runnable = new Runnable {
      def run() = promise.complete(Try(block))
    }
    
    config.ConfigNonBlocking.threadPoolSch.schedule(runnable, delay.length, delay.unit)
    
    promise.future
  }
}