package pl.olszak.michal.todo.util

import android.content.Context
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.data.model.Priority

/**
 * Created by molszak.
 * 27.01.2018
 */
object TodoUtil {

    @ColorInt
    fun getColorForPriority(context: Context, priority: Priority): Int {
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

}