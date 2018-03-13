package pl.olszak.michal.todo.view.preferences

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.cache.model.ThemePalette
import pl.olszak.michal.todo.view.ThemeGroup

/**
 * @author molszak
 *         created on 13.03.2018.
 */
class ThemePreference @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val themeGroup: ThemeGroup
    var onThemeChangeListener : ThemeGroup.OnChangeTheme? = null
        set(value) {
            themeGroup.onThemeChangeListener = value
        }

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.preference_theme, this)
        themeGroup = findViewById(R.id.theme_group)
    }

    fun setTheme(themePalette: ThemePalette){
        themeGroup.setTheme(themePalette)
    }

}