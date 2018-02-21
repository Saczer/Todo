package pl.olszak.michal.todo.tasks.create

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.text.TextUtils
import io.reactivex.disposables.CompositeDisposable
import pl.olszak.michal.todo.domain.interactor.task.AddQuickTask
import pl.olszak.michal.todo.tasks.navigation.TasksNavigator
import javax.inject.Inject

/**
 * Created by molszak.
 * 10.02.2018
 */
class QuickCreateTaskViewModel @Inject constructor(
        private val addQuickTask: AddQuickTask,
        private val navigator: TasksNavigator) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val loading: ObservableBoolean = ObservableBoolean(false)
    val taskTitle: ObservableField<String> = ObservableField()

    fun onNavigateBack() {
        navigator.handleOnBackPressed()
    }

    fun createTask() {
        taskTitle.get().let {
            if (TextUtils.isEmpty(it)) {
                onNavigateBack()
            } else {
                loading.set(true)
                compositeDisposable.add(
                        addQuickTask.execute(it)
                                .subscribe {
                                    loading.set(false)
                                    onNavigateBack()
                                })
            }
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}