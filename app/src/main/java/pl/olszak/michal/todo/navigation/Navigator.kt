package pl.olszak.michal.todo.navigation

import android.view.View

/**
 * @author molszak
 *         created on 31.01.2018.
 */
interface Navigator {

    fun toSettings()

    fun toTaskList()

    fun restartSettingsChange()

    fun toQuickCreateTask(view : View)

}