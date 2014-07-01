package config

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContextExecutorService
import java.util.concurrent.ScheduledExecutorService

object ConfigBlocking extends Config(numberOfThreads = 300)
object ConfigNonBlocking extends Config(numberOfThreads = 1)

class Config(numberOfThreads: Int) {
  val threadPool:ExecutorService = Executors.newFixedThreadPool(numberOfThreads)
  implicit val executionContext: ExecutionContextExecutorService = ExecutionContext.fromExecutorService(threadPool)
  
  val threadPoolSch: ScheduledExecutorService = Executors.newScheduledThreadPool(numberOfThreads)    
  implicit val executionContextSch: ExecutionContextExecutorService = ExecutionContext.fromExecutorService(threadPoolSch)
}