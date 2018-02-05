package pl.olszak.michal.todo.settings

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import pl.olszak.michal.todo.cache.dao.TodoPreferences
import pl.olszak.michal.todo.cache.model.ThemePalette
import pl.olszak.michal.todo.navigation.Navigator
import javax.inject.Inject

/**
 * @author molszak
 *         created on 05.02.2018.
 */
class SettingsViewModel @Inject constructor(
        private val todoPreferences: TodoPreferences,
        private val navigator: Navigator) : SettingsContract, ViewModel() {

    override val observableTheme: ObservableField<ThemePalette> = ObservableField()

    init {
        observableTheme.set(todoPreferences.getThemeColor())
    }

    override fun onThemeSelected(themePalette: ThemePalette) {
        todoPreferences.setThemePalette(themePalette)
        navigator.needsRestartSettingsChange()
    }
}