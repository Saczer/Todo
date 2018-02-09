package pl.olszak.michal.todo.tasks.navigation

import android.os.Bundle
import android.view.View

/**
 * @author molszak
 *         created on 31.01.2018.
 */
interface TasksNavigator {

    fun onRestoreInstanceState(savedInstanceState: Bundle?)

    interface NavigatorInteractionCallback {

        fun hideAction(hide: Boolean)

        fun hideNavigation(hide: Boolean)

    }

    fun attach(navigationCallback: NavigatorInteractionCallback)

    fun detach(navigationCallback: NavigatorInteractionCallback)

    fun toSettings()

    fun toTaskList()

    fun restartSettingsChange()

    fun toQuickCreateTask(view: View)

    fun handleOnBackPressed(): Boolean

    fun returnFromCreateTask()

}