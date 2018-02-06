package pl.olszak.michal.todo.util.extension

import android.content.Context
import android.graphics.RectF
import android.view.View

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

fun View.calculateBounds(): RectF {
    val availableWidth = this.width - this.paddingLeft - this.paddingRight
    val availableHeight = this.height - this.paddingTop - this.paddingBottom

    val sideLength = Math.min(availableWidth, availableHeight)

    val left = this.paddingLeft + (availableWidth - sideLength) / 2f
    val top = this.paddingTop + (availableHeight - sideLength) / 2f

    return RectF(left, top, left + sideLength, top + sideLength)
}