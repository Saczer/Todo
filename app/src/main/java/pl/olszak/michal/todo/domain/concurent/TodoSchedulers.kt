package pl.olszak.michal.todo.domain.concurent

import io.reactivex.Scheduler

/**
 * @author molszak
 *         created on 18.01.2018.
 */
interface TodoSchedulers {

    fun post(): Scheduler

    fun threadExecutor(): Scheduler

}