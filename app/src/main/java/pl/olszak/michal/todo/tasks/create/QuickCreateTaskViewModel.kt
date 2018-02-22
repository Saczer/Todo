package pl.olszak.michal.todo.tasks.create

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.text.TextUtils
import pl.olszak.michal.todo.domain.interactor.task.AddQuickTask
import pl.olszak.michal.todo.tasks.navigation.TasksNavigator
import pl.olszak.michal.todo.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * Created by molszak.
 * 10.02.2018
 */
class QuickCreateTaskViewModel @Inject constructor(
        private val addQuickTask: AddQuickTask,
        private val navigator: TasksNavigator) : BaseViewModel() {

    val loading: ObservableBoolean = ObservableBoolean(false)
    val taskTitle: ObservableField<String> = ObservableField()

    override fun start() {
    }

    fun onNavigateBack() {
        navigator.handleOnBackPressed()
    }

    fun createTask() {
        taskTitle.get().let {
            if (TextUtils.isEmpty(it)) {
                onNavigateBack()
            } else {
                disposables.add(
                        addQuickTask.execute(it)
                                .doOnSubscribe {
                                    loading.set(true)
                                }
                                .subscribe {
                                    loading.set(false)
                                    onNavigateBack()
                                })
            }
        }
    }

}