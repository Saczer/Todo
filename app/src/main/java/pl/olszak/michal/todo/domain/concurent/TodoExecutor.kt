package pl.olszak.michal.todo.domain.concurent

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

/**
 * @author molszak
 *         created on 18.01.2018.
 */
class TodoExecutor @Inject constructor() : ThreadExecutor {

    private val queue: LinkedBlockingQueue<Runnable> = LinkedBlockingQueue()
    private val threadPoolExecutor: ThreadPoolExecutor
    private val threadFactory: ThreadFactory

    init {
        threadFactory = JobThreadFactory()
        threadPoolExecutor = ThreadPoolExecutor(INITIAL_POOL_SIZE,
                MAX_POOL_SIZE,
                ALIVE_TIME,
                TimeUnit.SECONDS,
                queue,
                threadFactory)
    }

    override fun execute(command: Runnable?) {
        if (command == null) {
            throw IllegalArgumentException("Runnable cannot be null")
        }
        threadPoolExecutor.execute(command)
    }

    private class JobThreadFactory : ThreadFactory {

        private val counter = AtomicInteger()

        override fun newThread(runnable: Runnable?): Thread {
            val thread = Thread(runnable)
            thread.name = NAME + counter.incrementAndGet()
            return thread
        }

        companion object {
            private const val NAME = "todo-thread-"
        }
    }

    companion object {
        private val CPU_COUNT = Runtime.getRuntime().availableProcessors()
        private const val INITIAL_POOL_SIZE = 2
        //from AsyncTask
        private val MAX_POOL_SIZE = Math.max(INITIAL_POOL_SIZE, Math.min(CPU_COUNT - 1, 4))
        private const val ALIVE_TIME = 10L
    }


}