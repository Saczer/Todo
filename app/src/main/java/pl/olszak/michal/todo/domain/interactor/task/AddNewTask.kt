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
class AddNewTask @Inject constructor(private val taskStore: TaskStore,
                                     schedulers: TodoSchedulers
) : CompletableUseCase<Task>(schedulers) {


    override fun buildUseCaseCompletable(params: Task?): Completable {
        if (params == null) {
            return Completable.complete()
        }
        return taskStore.addTask(params)
    }

}