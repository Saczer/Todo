package pl.olszak.michal.todo.viewmodel.binding

import android.databinding.BindingAdapter
import android.databinding.BindingMethod
import android.databinding.BindingMethods
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import org.threeten.bp.Instant
import org.threeten.bp.format.DateTimeFormatter
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.data.model.Priority
import pl.olszak.michal.todo.util.extension.logE
import pl.olszak.michal.todo.util.tools.TodoUtils
import pl.olszak.michal.todo.view.circle.CircleView
import pl.olszak.michal.todo.view.circle.ThemeGroup
import pl.olszak.michal.todo.viewmodel.BindingRecyclerAdapter
import java.text.NumberFormat
import java.text.ParseException

/**
 * Created by molszak.
 * 10.02.2018
 */

@BindingMethods(value = [
    (BindingMethod(type = ThemeGroup::class,
            attribute = "themePalette",
            method = "setTheme"))])
class BindingMethods


@BindingAdapter(value = ["themePaletteChange"], requireAll = false)
fun bindThemePaletteChange(view: ThemeGroup, listener: ThemeGroup.OnChangeTheme?) {
    listener?.let {
        if (view.onThemeChangeListener != null && view.onThemeChangeListener == it) {
            return
        }

        view.onThemeChangeListener = it
    }
}

@BindingAdapter(value = ["android:visibility"], requireAll = false)
fun bindVisibility(view: FloatingActionButton, visibility: Boolean?) {
    visibility?.let {
        if (it) {
            view.isClickable = true
            view.show()
        } else {
            view.isClickable = false
            view.hide()
        }
    }
}

@BindingAdapter(value = ["priority"], requireAll = false)
fun bindPriority(view: ImageView, priority: Priority?) {
    val color = if (priority != null) {
        TodoUtils.getColor(view.context, priority)
    } else {
        ContextCompat.getColor(view.context, R.color.white)
    }
    view.setBackgroundColor(color)
}

@BindingAdapter(value = ["priority"], requireAll = false)
fun bindPriority(view: CircleView, priority: Priority?) {
    priority?.let {
        val color = TodoUtils.getColor(view.context, it)
        view.setColor(color)
    }
}

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
        view.text = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(it)
    }
}

private fun getNumberFormat(view: View): NumberFormat {
    val resources = view.resources
    val locale = resources.configuration.locale
    return NumberFormat.getNumberInstance(locale)
}

@BindingAdapter(value = ["adapter"], requireAll = false)
fun bindAdapter(view: RecyclerView, adapter: BindingRecyclerAdapter?) {
    adapter?.let {
        view.adapter = it
        view.layoutManager = LinearLayoutManager(view.context)
    }
}