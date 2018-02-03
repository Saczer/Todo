package pl.olszak.michal.todo.util

import android.content.Context
import android.graphics.Color
import android.graphics.RectF
import android.support.annotation.ColorInt
import android.support.annotation.FloatRange
import android.support.annotation.StyleRes
import android.support.v4.content.ContextCompat
import android.view.View
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.cache.model.ThemePalette
import pl.olszak.michal.todo.data.model.Priority

/**
 * Created by molszak.
 * 27.01.2018
 */
object TodoUtils {

    @ColorInt
    fun getColor(context: Context, priority: Priority): Int {
        return when (priority) {
            Priority.CRITICAL -> ContextCompat.getColor(context, R.color.deepOrange)
            Priority.HIGH -> ContextCompat.getColor(context, R.color.orange)
            Priority.MEDIUM -> ContextCompat.getColor(context, R.color.yellow)
            else -> ContextCompat.getColor(context, R.color.white)
        }
    }

    @ColorInt
    fun getComplementaryColor(@ColorInt color: Int): Int {
        return (0xffffff - color)
    }

    @ColorInt
    fun darkenColor(@ColorInt color: Int, @FloatRange(from = 0.0, to = 1.0) factor: Float): Int {
        val a = Color.alpha(color)
        val r = Math.round(Color.red(color) * factor)
        val g = Math.round(Color.green(color) * factor)
        val b = Math.round(Color.blue(color) * factor)
        return Color.argb(a,
                Math.min(r, 255),
                Math.min(g, 255),
                Math.min(b, 255)
        )
    }

    @StyleRes
    fun getStyle(themePalette: ThemePalette): Int {
        return when (themePalette) {
            ThemePalette.PINK -> R.style.NormalTheme
            ThemePalette.ORANGE -> R.style.LightTheme
            ThemePalette.BLACK -> R.style.NormalTheme
        }
    }

    @ColorInt
    fun getColor(context: Context, palette: ThemePalette): Int {
        return when (palette) {
            ThemePalette.ORANGE -> ContextCompat.getColor(context, R.color.deepOrange)
            ThemePalette.PINK -> ContextCompat.getColor(context, R.color.pink)
            ThemePalette.BLACK -> ContextCompat.getColor(context, R.color.black)
        }
    }

}

fun dpToPx(context: Context, value: Float): Float {
    return value * context.resources.displayMetrics.density
}

fun isMatchParent(measureSpec: Int): Boolean {
    return View.MeasureSpec.getMode(measureSpec) == View.MeasureSpec.EXACTLY
}

fun calculateBounds(view : View): RectF {
    val availableWidth = view.width - view.paddingLeft - view.paddingRight
    val availableHeight = view.height - view.paddingTop - view.paddingBottom

    val sideLength = Math.min(availableWidth, availableHeight)

    val left = view.paddingLeft + (availableWidth - sideLength) / 2f
    val top = view.paddingTop + (availableHeight - sideLength) / 2f

    return RectF(left, top, left + sideLength, top + sideLength)
}