package pl.olszak.michal.todo.domain.interactor.task

import io.reactivex.Flowable
import pl.olszak.michal.todo.data.TaskStore
import pl.olszak.michal.todo.data.model.Task
import pl.olszak.michal.todo.domain.concurent.TodoSchedulers
import pl.olszak.michal.todo.domain.interactor.FlowableUseCase
import javax.inject.Inject

/**
 * Created by molszak.
 * 29.01.2018
 */
class GetAllTasks @Inject constructor(
        private val taskStore: TaskStore,
        todoSchedulers: TodoSchedulers) : FlowableUseCase<List<Task>, Unit>(todoSchedulers) {

    override fun buildUseCase(params: Unit?): Flowable<List<Task>> {
        return taskStore.getAllTasks()
    }


}