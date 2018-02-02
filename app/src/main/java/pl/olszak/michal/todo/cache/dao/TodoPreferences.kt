package pl.olszak.michal.todo.cache.dao

import pl.olszak.michal.todo.cache.model.ThemePalette

/**
 * @author molszak
 *         created on 02.02.2018.
 */
interface TodoPreferences {

    fun getThemeColor(): ThemePalette

    fun setThemePalette(themeColor: ThemePalette)

}