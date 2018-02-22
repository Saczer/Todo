package pl.olszak.michal.todo.domain.interactor

import io.reactivex.Flowable
import pl.olszak.michal.todo.domain.concurent.TodoSchedulers

/**
 * Created by molszak.
 * 29.01.2018
 */
abstract class FlowableUseCase<T, in Params> constructor(private val todoSchedulers: TodoSchedulers) {

    protected abstract fun buildUseCase(params: Params?): Flowable<T>

    fun execute(params: Params? = null): Flowable<T> {
        return buildUseCase(params)
                .subscribeOn(todoSchedulers.io())
                .observeOn(todoSchedulers.ui())
    }
}