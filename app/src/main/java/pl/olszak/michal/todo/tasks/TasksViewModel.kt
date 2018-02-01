package pl.olszak.michal.todo.tasks

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.support.design.widget.Snackbar
import android.view.MenuItem
import android.view.View
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.navigation.Navigator
import javax.inject.Inject

/**
 * @author molszak
 *         created on 31.01.2018.
 */
class TasksViewModel @Inject constructor() : ViewModel(), TasksContract {

    override val visibility: ObservableField<Boolean> = ObservableField(true)
    private var navigator: Navigator? = null

    override fun onClickAdd(view: View) {
        navigator?.changeTheme()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.settings -> {
                navigator?.let {
                    it.navigateToSettings()
                    visibility.set(false)
                }
                return true
            }
            R.id.task_list -> {
                navigator?.let {
                    it.navigateToTaskList()
                    visibility.set(true)
                }
                return true
            }
            else -> return false
        }
    }

    fun setNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    override fun onCleared() {
        navigator = null
    }
}