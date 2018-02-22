package pl.olszak.michal.todo.tasks.tasklist

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import io.reactivex.android.schedulers.AndroidSchedulers
import pl.olszak.michal.todo.data.model.Task
import pl.olszak.michal.todo.domain.interactor.task.GetAllTasks
import pl.olszak.michal.todo.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * @author molszak
 *         created on 22.02.2018.
 */
class TasksListViewModel @Inject constructor(
        private val getAllTasks: GetAllTasks) : BaseViewModel() {

    val loading: ObservableBoolean = ObservableBoolean(false)
    val shouldShowError: ObservableBoolean = ObservableBoolean(false)
    val tasks: ObservableList<Task> = ObservableArrayList()

    override fun start() {
        tasks.clear()
        disposables.add(getAllTasks.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    loading.set(true)
                }.subscribe({
                    tasks.clear()
                    tasks.addAll(it)
                }, {
                    shouldShowError.set(true)
                }))
    }


}