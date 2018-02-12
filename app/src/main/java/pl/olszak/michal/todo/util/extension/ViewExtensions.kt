package pl.olszak.michal.todo.util.extension

import android.content.Context
import android.graphics.RectF
import android.support.annotation.ColorInt
import android.support.v4.view.ViewCompat
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.content.systemService

/**
 * @author molszak
 *         created on 06.02.2018.
 */
fun dpToPx(context: Context, value: Float): Float {
    return value * context.resources.displayMetrics.density
}

fun isMatchParent(measureSpec: Int): Boolean {
    return View.MeasureSpec.getMode(measureSpec) == View.MeasureSpec.EXACTLY
}

fun shouldAnimateVisibilityChange(view: View): Boolean {
    return ViewCompat.isLaidOut(view) && !view.isInEditMode
}

fun View.hideSoftInputMethod(millis: Long = 0L) {
    val runnable = Runnable {
        val inputManager: InputMethodManager = context.systemService()
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    if (millis > 0) {
        this.postDelayed(runnable, millis)
    } else {
        this.post(runnable)
    }
}

fun View.showSoftInputMethod(millis: Long = 0L) {
    if (isFocusable) {
        if (requestFocus()) {
            val runnable = Runnable {
                val inputManager: InputMethodManager = context.systemService()
                inputManager.showSoftInput(this, android.view.inputmethod.InputMethod.SHOW_EXPLICIT)
            }

            if (millis > 0) {
                this.postDelayed(runnable, millis)
            } else {
                this.post(runnable)
            }
        }
    }
}

@ColorInt
fun getCurrentAccentColor(context: Context): Int {
    val value = TypedValue()
    context.theme.resolveAttribute(android.R.attr.colorAccent, value, true)
    return value.data
}

fun View.calculateBounds(): RectF {
    val availableWidth = this.width - this.paddingLeft - this.paddingRight
    val availableHeight = this.height - this.paddingTop - this.paddingBottom

    val sideLength = Math.min(availableWidth, availableHeight)

    val left = this.paddingLeft + (availableWidth - sideLength) / 2f
    val top = this.paddingTop + (availableHeight - sideLength) / 2f

    return RectF(left, top, left + sideLength, top + sideLength)
}