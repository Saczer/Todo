package pl.olszak.michal.todo.navigation

/**
 * @author molszak
 *         created on 31.01.2018.
 */
interface Navigator {

    fun toSettings()

    fun toTaskList()

    fun restartSettingsChange()

    fun toCreateTask()

}