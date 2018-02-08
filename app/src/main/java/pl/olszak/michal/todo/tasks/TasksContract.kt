package pl.olszak.michal.todo.tasks

import android.databinding.ObservableField
import android.view.MenuItem
import android.view.View

/**
 * @author molszak
 *         created on 31.01.2018.
 */
interface TasksContract {
    val actionVisibility: ObservableField<Boolean>
    val navigationVisibility: ObservableField<Boolean>

    fun onClickAdd(view: View)

    fun onNavigationItemSelected(menuItem: MenuItem): Boolean

}