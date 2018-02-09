package pl.olszak.michal.todo.tasks.settings

import android.databinding.ObservableField
import pl.olszak.michal.todo.cache.model.ThemePalette

/**
 * @author molszak
 *         created on 05.02.2018.
 */
interface SettingsContract {

    val observableTheme: ObservableField<ThemePalette>

    fun onThemeSelected(themePalette: ThemePalette)

}