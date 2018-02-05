package pl.olszak.michal.todo.view.circle

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.cache.model.ThemePalette

/**
 * Created by molszak.
 * 04.02.2018
 */
//TODO: this shuld not be created like this, it should asseble views from ThemePalette enum
class ThemeGroup @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), ThemeView.OnStateChangeListener {

    var onThemeChangeListener: OnChangeTheme? = null

    private val orange: ThemeView
    private val pink: ThemeView
    private val black: ThemeView

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL

        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.theme_group, this, true)

        orange = findViewById(R.id.orange)
        orange.apply {
            themePalette = ThemePalette.ORANGE
            listener = this@ThemeGroup
        }

        pink = findViewById(R.id.pink)
        pink.apply {
            themePalette = ThemePalette.PINK
            listener = this@ThemeGroup
        }

        black = findViewById(R.id.black)
        black.apply {
            themePalette = ThemePalette.BLACK
            listener = this@ThemeGroup
        }
    }

    override fun onStateChange(view: ThemeView, checked: Boolean) {
        (0..childCount)
                .map { getChildAt(it) }
                .filterIsInstance<ThemeView>()
                .forEach {
                    if (it != view && it.isChecked) {
                        it.isChecked = false
                    }
                }

        view.themePalette?.let {
            onThemeChangeListener?.onThemeChange(it)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        onThemeChangeListener = null
    }

    fun setTheme(themePalette: ThemePalette) {
        (0..childCount)
                .map { getChildAt(it) }
                .filterIsInstance<ThemeView>()
                .forEach { view ->
                    view.themePalette?.let {
                        if (it == themePalette) {
                            if (!view.isChecked) {
                                view.isChecked = true
                            }
                        } else {
                            view.isChecked = false
                        }
                    }
                }
    }

    interface OnChangeTheme {

        fun onThemeChange(themePalette: ThemePalette)

    }
}