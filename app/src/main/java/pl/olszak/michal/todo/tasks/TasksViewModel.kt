package pl.olszak.michal.todo.tasks

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.view.MenuItem
import android.view.View
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.tasks.navigation.TasksNavigator
import javax.inject.Inject

/**
 * @author molszak
 *         created on 31.01.2018.
 */
class TasksViewModel @Inject constructor() : ViewModel(), TasksNavigator.NavigatorInteractionCallback {
    val actionVisibility: ObservableField<Boolean> = ObservableField(true)
    val navigationVisibility: ObservableField<Boolean> = ObservableField(true)

    var navigator: TasksNavigator? = null
        set(value) {
            value?.attach(this)
            field = value
        }

    fun handleChangeSettings() {
        navigator?.toSettings()
    }

    fun handleOnBackPressed(): Boolean {
        return navigator?.handleOnBackPressed() ?: false
    }

    fun handleNoSavedState() {
        navigator?.toTaskList()
    }

    fun onClickAdd(view: View) {
        navigator?.toQuickCreateTask(view)
    }

    fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.settings -> {
                navigator?.toSettings()
                true
            }
            R.id.task_list -> {
                navigator?.toTaskList()
                true
            }
            else -> false
        }
    }

    override fun hideAction(hide: Boolean) {
        actionVisibility.set(!hide)
    }

    override fun hideNavigation(hide: Boolean) {
        navigationVisibility.set(!hide)
    }

    override fun onCleared() {
        navigator?.detach(this)
        navigator = null
    }
}