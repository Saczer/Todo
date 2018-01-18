package pl.olszak.michal.todo.domain.concurent

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * @author molszak
 *         created on 18.01.2018.
 */
class UiThread @Inject internal constructor() : PostExecutionThread {
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}