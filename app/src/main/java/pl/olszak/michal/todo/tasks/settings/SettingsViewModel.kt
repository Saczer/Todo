package pl.olszak.michal.todo.tasks.settings

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import pl.olszak.michal.todo.cache.dao.TodoPreferences
import pl.olszak.michal.todo.cache.model.ThemePalette
import pl.olszak.michal.todo.tasks.navigation.TasksNavigator
import pl.olszak.michal.todo.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * @author molszak
 *         created on 05.02.2018.
 */
class SettingsViewModel @Inject constructor(
        private val todoPreferences: TodoPreferences,
        private val navigator: TasksNavigator) : BaseViewModel() {


    val observableTheme: ObservableField<ThemePalette> = ObservableField()

    override fun start() {
        observableTheme.set(todoPreferences.getThemeColor())
    }

    fun onThemeSelected(themePalette: ThemePalette) {
        todoPreferences.setThemePalette(themePalette)
        navigator.restartSettingsChange()
    }
}