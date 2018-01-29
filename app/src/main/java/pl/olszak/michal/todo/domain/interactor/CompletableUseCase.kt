package pl.olszak.michal.todo.domain.interactor

import io.reactivex.Completable
import pl.olszak.michal.todo.domain.concurent.TodoSchedulers

/**
 * @author molszak
 *         created on 29.01.2018.
 */
abstract class CompletableUseCase<in Params> constructor(
        private val schedulers: TodoSchedulers) {

    protected abstract fun buildUseCaseCompletable(params: Params? = null): Completable

    fun execute(params: Params?): Completable {
        return buildUseCaseCompletable(params)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())

    }
}