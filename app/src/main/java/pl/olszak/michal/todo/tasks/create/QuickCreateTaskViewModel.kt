package pl.olszak.michal.todo.tasks.create

import android.arch.lifecycle.ViewModel
import android.view.View
import pl.olszak.michal.todo.tasks.navigation.TasksNavigator
import javax.inject.Inject

/**
 * Created by molszak.
 * 10.02.2018
 */
class QuickCreateTaskViewModel @Inject constructor(
        private val navigator: TasksNavigator) : ViewModel() {

    fun onNavigateBack() {
        navigator.handleOnBackPressed()
    }

}