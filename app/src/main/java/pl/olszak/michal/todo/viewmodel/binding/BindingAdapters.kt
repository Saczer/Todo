package pl.olszak.michal.todo.viewmodel.binding

import android.databinding.BindingAdapter
import android.databinding.BindingMethod
import android.databinding.BindingMethods
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.View
import android.widget.TextView
import org.threeten.bp.Instant
import org.threeten.bp.format.DateTimeFormatter
import pl.olszak.michal.todo.data.model.Priority
import pl.olszak.michal.todo.data.model.Task
import pl.olszak.michal.todo.tasks.tasklist.adapter.TaskAdapter
import pl.olszak.michal.todo.util.tools.TodoUtils
import pl.olszak.michal.todo.view.CircleView
import pl.olszak.michal.todo.view.ThemeGroup
import pl.olszak.michal.todo.view.animation.TodoAnimationUtils
import pl.olszak.michal.todo.view.preferences.ThemePreference

/**
 * Created by molszak.
 * 10.02.2018
 */
@BindingMethods(value = [
    BindingMethod(type = ThemePreference::class,
            attribute = "themePalette",
            method = "setTheme")
])
class BindingMethods


@BindingAdapter(value = ["themePaletteChange"], requireAll = false)
fun bindThemePaletteChange(view: ThemePreference, listener: ThemeGroup.OnChangeTheme?) {
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

@BindingAdapter(value = ["animatedVisibility"], requireAll = false)
fun bindVisibility(view: BottomNavigationView, animatedVisibility: Boolean?) {
    animatedVisibility?.let {
        TodoAnimationUtils.animateVisibility(view, it)
    }
}

@BindingAdapter(value = ["android:visibility"], requireAll = false)
fun bindVisibility(view: View, visibility: Boolean?) {
    visibility?.let {
        view.visibility = if (it) View.VISIBLE else View.GONE
    }
}

@BindingAdapter(value = ["priority"], requireAll = false)
fun bindPriority(view: CircleView, priority: Priority?) {
    priority?.let {
        val color = TodoUtils.getColor(view.context, it)
        view.setColor(color)
    }
}

@BindingAdapter(value = ["done"], requireAll = false)
fun bindDone(view: TextView, done: Boolean?) {
    done?.let {
        if (it) {
            val spannable: Spannable = SpannableString(view.text)
            spannable.setSpan(StrikethroughSpan(), 0, spannable.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            view.text = spannable
        }
    }
}

@BindingAdapter(value = ["android:text"], requireAll = false)
fun bindInstant(view: TextView, instant: Instant?) {
    instant?.let {
        view.text = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(it)
    }
}

@BindingAdapter(value = ["android:visibility"], requireAll = false)
fun bindVisibility(view: CircleView, visible: Boolean?) {
    visible?.let {
        view.visibility = if (it) View.VISIBLE else View.GONE
    }
}

@BindingAdapter(value = ["tasks"])
fun bindTaskList(view: RecyclerView, items: List<Task>) {
    val adapter: TaskAdapter? = view.adapter as? TaskAdapter?
    adapter?.setItems(items)
}

@BindingAdapter(value = ["android:layout_height"])
fun bindLayoutHeight(view: View, height : Float){
    val params = view.layoutParams
    params.height = height.toInt()
    view.layoutParams = params
}
