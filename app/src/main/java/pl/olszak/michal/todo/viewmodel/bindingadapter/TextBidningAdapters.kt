package pl.olszak.michal.todo.viewmodel.bindingadapter

import android.databinding.BindingAdapter
import android.view.View
import android.widget.TextView
import timber.log.Timber
import java.text.NumberFormat
import java.text.ParseException

/**
 * @author molszak
 *         created on 19.01.2018.
 */

@BindingAdapter(value = ["android:text"], requireAll = true)
fun bindText(view: TextView, number: Int) {
    val format = getNumberFormat(view)
    try {
        val text = format.format(number)
        view.text = text
    } catch (ex: ParseException) {
        Timber.e(ex)

    }
}

@SuppressWarnings("deprecation")
private fun getNumberFormat(view: View): NumberFormat {
    val resources = view.resources
    val locale = resources.configuration.locale
    return NumberFormat.getNumberInstance(locale)
}
