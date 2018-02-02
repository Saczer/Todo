package pl.olszak.michal.todo.cache

import android.content.Context
import pl.olszak.michal.todo.cache.dao.TodoPreferences
import pl.olszak.michal.todo.cache.model.ThemePalette
import javax.inject.Inject

/**
 * @author molszak
 *         created on 02.02.2018.
 */
class TodoPreferencesImpl @Inject constructor(context: Context) : TodoPreferences {

    private val sharedPrefs = SharedPrefs(context)

    override fun getThemeColor(): ThemePalette {
        val themeName = sharedPrefs.get(Keys.THEME, Defaults.THEME)
        return ThemePalette.getThemeByName(themeName)
    }

    override fun setThemePalette(themeColor: ThemePalette) {
        sharedPrefs.put(Keys.THEME, themeColor.name)
    }

}