package pl.olszak.michal.todo.domain.interactor.task

import io.reactivex.Completable
import pl.olszak.michal.todo.data.TaskStore
import pl.olszak.michal.todo.data.model.Task
import pl.olszak.michal.todo.domain.concurent.TodoSchedulers
import pl.olszak.michal.todo.domain.interactor.CompletableUseCase
import javax.inject.Inject

/**
 * @author molszak
 *         created on 29.01.2018.
 */
class AlterTask @Inject constructor(
        private val taskStore: TaskStore,
        schedulers: TodoSchedulers
) : CompletableUseCase<Task>(schedulers) {


    public override fun buildUseCaseCompletable(params: Task?): Completable {
        params?.let {
            return taskStore.addTask(it)
        }
        return Completable.error(NullPointerException("Provided task should not be null"))
    }

}