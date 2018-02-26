package pl.olszak.michal.todo.tasks.tasklist

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import pl.olszak.michal.todo.data.model.Task
import pl.olszak.michal.todo.domain.interactor.task.AlterTask
import pl.olszak.michal.todo.domain.interactor.task.GetAllTasks
import pl.olszak.michal.todo.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * @author molszak
 *         created on 22.02.2018.
 */
class TasksListViewModel @Inject constructor(
        private val getAllTasks: GetAllTasks,
        private val alterTask: AlterTask) : BaseViewModel() {

    val loading: ObservableBoolean = ObservableBoolean(false)
    val shouldShowError: ObservableBoolean = ObservableBoolean(false)
    val tasks: ObservableList<Task> = ObservableArrayList()

    override fun start() {
        tasks.clear()
        disposables.add(getAllTasks.execute()
                .doOnSubscribe {
                    loading.set(true)
                }.subscribe({
                    tasks.clear()
                    tasks.addAll(it)
                }, {
                    shouldShowError.set(true)
                }))
    }

    fun markTaskAsChecked(task: Task) {
        //todo: could be done in model, with returned new instance of task
        val altered = Task(
                title = task.title,
                description = task.description,
                done = true,
                id = task.id,
                priority = task.priority,
                repeating = task.repeating,
                time = task.time)

        disposables.add(alterTask.execute(altered)
                .doOnSubscribe {
                    loading.set(true)
                }.subscribe({
                    loading.set(false)
                }))
    }


}