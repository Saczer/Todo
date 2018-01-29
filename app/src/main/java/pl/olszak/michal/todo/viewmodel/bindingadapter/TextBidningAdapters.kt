package pl.olszak.michal.todo.viewmodel.bindingadapter

import android.databinding.BindingAdapter
import android.view.View
import android.widget.TextView
import org.threeten.bp.Instant
import org.threeten.bp.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME
import pl.olszak.michal.todo.util.logE
import java.text.NumberFormat
import java.text.ParseException

/**
 * @author molszak
 *         created on 19.01.2018.
 */

@BindingAdapter(value = ["android:text"], requireAll = false)
fun bindText(view: TextView, number: Int) {
    val format = getNumberFormat(view)
    try {
        val text = format.format(number)
        view.text = text
    } catch (ex: ParseException) {
        logE(ex) {
            "Could not parse number"
        }
    }
}

@BindingAdapter(value = ["android:text"], requireAll = false)
fun bindInstant(view: TextView, instant: Instant?) {
    instant?.let {
        view.text = ISO_LOCAL_DATE_TIME.format(it)
    }
}

@SuppressWarnings("deprecation")
private fun getNumberFormat(view: View): NumberFormat {
    val resources = view.resources
    val locale = resources.configuration.locale
    return NumberFormat.getNumberInstance(locale)
}
