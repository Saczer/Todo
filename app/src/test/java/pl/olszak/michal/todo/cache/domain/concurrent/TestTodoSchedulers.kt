package pl.olszak.michal.todo.cache.domain.concurrent

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import pl.olszak.michal.todo.domain.concurent.TodoSchedulers
import javax.inject.Inject

/**
 * @author molszak
 *         created on 26.01.2018.
 */
class TestTodoSchedulers @Inject constructor() : TodoSchedulers {
    override fun post(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun threadExecutor(): Scheduler {
        return Schedulers.trampoline()
    }
}