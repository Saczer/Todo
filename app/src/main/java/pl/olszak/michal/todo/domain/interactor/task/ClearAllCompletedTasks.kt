package pl.olszak.michal.todo.domain.interactor.task

import io.reactivex.Completable
import pl.olszak.michal.todo.data.TaskStore
import pl.olszak.michal.todo.domain.concurent.TodoSchedulers
import pl.olszak.michal.todo.domain.interactor.CompletableUseCase
import javax.inject.Inject

/**
 * @author molszak
 *         created on 26.02.2018.
 */
class ClearAllCompletedTasks @Inject constructor(
        private val taskStore: TaskStore,
        schedulers: TodoSchedulers) : CompletableUseCase<Unit>(schedulers) {

    public override fun buildUseCaseCompletable(params: Unit?): Completable {
        return taskStore.clearAllCompleted()
    }

}