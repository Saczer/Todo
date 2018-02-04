package pl.olszak.michal.todo.view.circle

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.cache.TodoPreferencesImpl
import pl.olszak.michal.todo.cache.dao.TodoPreferences
import pl.olszak.michal.todo.cache.model.ThemePalette

/**
 * Created by molszak.
 * 04.02.2018
 */
class ThemeGroup @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val preferences: TodoPreferences = TodoPreferencesImpl(context)

    lateinit var orange: ThemeView
    lateinit var pink: ThemeView
    lateinit var black: ThemeView

    init {
        orientation = HORIZONTAL
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.theme_group, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        orange.themePalette = ThemePalette.ORANGE
        pink.themePalette = ThemePalette.PINK
        black.themePalette = ThemePalette.BLACK

        val theme = preferences.getThemeColor()
        when (theme) {
            ThemePalette.ORANGE -> orange.changeState()
            ThemePalette.PINK -> pink.changeState()
            ThemePalette.BLACK -> black.changeState()
        }


    }
}