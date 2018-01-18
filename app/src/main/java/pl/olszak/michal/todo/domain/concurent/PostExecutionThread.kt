package pl.olszak.michal.todo.domain.concurent

import io.reactivex.Scheduler

/**
 * @author molszak
 *         created on 18.01.2018.
 */
interface PostExecutionThread {

    val scheduler: Scheduler

}