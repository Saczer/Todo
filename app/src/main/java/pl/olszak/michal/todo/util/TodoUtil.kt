package pl.olszak.michal.todo.util

import android.content.Context
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.data.model.Icon
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

    @DrawableRes
    fun getIconResource(icon: Icon): Int? {
        return when (icon) {
            Icon.HOUSE -> R.drawable.ic_home
            Icon.MEAL -> R.drawable.ic_silverware
            Icon.PET -> R.drawable.ic_paw
            Icon.RELAX -> R.drawable.ic_yin_yang
            Icon.WORK -> R.drawable.ic_briefcase
            Icon.SCHOOL -> R.drawable.ic_school
            else -> null
        }
    }

}