package pl.olszak.michal.todo.domain.concurent

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author molszak
 *         created on 18.01.2018.
 */
class TodoSchedulersFacade @Inject constructor(private val postExecutionThread: PostExecutionThread,
                                               private val threadExecutor: ThreadExecutor) : TodoSchedulers {
    override fun io(): Scheduler {
        return Schedulers.from(threadExecutor)
    }

    override fun ui(): Scheduler {
        return postExecutionThread.scheduler
    }
}