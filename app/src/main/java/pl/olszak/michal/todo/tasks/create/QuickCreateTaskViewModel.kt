package pl.olszak.michal.todo.tasks.create

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.text.TextUtils
import pl.olszak.michal.todo.tasks.navigation.TasksNavigator
import javax.inject.Inject

/**
 * Created by molszak.
 * 10.02.2018
 */
class QuickCreateTaskViewModel @Inject constructor(
        private val navigator: TasksNavigator) : ViewModel() {

    val taskTitle: ObservableField<String> = ObservableField()

    fun onNavigateBack() {
        navigator.handleOnBackPressed()
    }

    fun createTask() {
        if (TextUtils.isEmpty(taskTitle.get())) {
            onNavigateBack()
        }
    }

}