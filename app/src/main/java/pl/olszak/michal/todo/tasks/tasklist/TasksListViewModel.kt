package pl.olszak.michal.todo.tasks.tasklist

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import android.databinding.ObservableList
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.data.model.Task
import pl.olszak.michal.todo.domain.interactor.task.AlterTask
import pl.olszak.michal.todo.domain.interactor.task.ClearAllCompletedTasks
import pl.olszak.michal.todo.domain.interactor.task.DeleteTask
import pl.olszak.michal.todo.domain.interactor.task.GetAllTasks
import pl.olszak.michal.todo.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * @author molszak
 *         created on 22.02.2018.
 */
class TasksListViewModel @Inject constructor(
        private val getAllTasks: GetAllTasks,
        private val alterTask: AlterTask,
        private val deleteTask: DeleteTask,
        private val clearAllCompletedTasks: ClearAllCompletedTasks) : BaseViewModel() {

    val loading: ObservableBoolean = ObservableBoolean(false)
    val tasks: ObservableList<Task> = ObservableArrayList()
    val snackbarMessage: ObservableInt = ObservableInt()

    override fun start() {
        tasks.clear()
        disposables.add(getAllTasks.execute()
                .doOnSubscribe {
                    loading.set(true)
                }.subscribe({
                    loading.set(false)
                    tasks.clear()
                    tasks.addAll(it)
                }, {
                    snackbarMessage.set(R.string.error_task_list)
                }))
    }

    fun completeTask(task: Task) {
        val completed = task.complete()

        disposables.add(alterTask.execute(completed)
                .doOnSubscribe {
                    loading.set(true)
                }.subscribe({
                    loading.set(false)
                    snackbarMessage.set(R.string.task_completed)
                }, {
                    snackbarMessage.set(R.string.error_complete_task)
                }))
    }

    fun clearTask(task: Task){
        if(task.done){
            disposables.add(deleteTask.execute(task)
                    .doOnSubscribe {
                        loading.set(true)
                    }
                    .subscribe({
                        loading.set(false)
                        snackbarMessage.set(R.string.task_removed)
                    }, {
                        snackbarMessage.set(R.string.error_remove_task)
                    })
            )
        }
    }


    fun clearAllCompletedTasks() {
        disposables.add(clearAllCompletedTasks.execute()
                .subscribe({
                    snackbarMessage.set(R.string.task_removal_success)
                }))
    }


}