package pl.olszak.michal.todo.viewmodel.bindingadapter

import android.databinding.BindingAdapter
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.data.model.Priority
import pl.olszak.michal.todo.util.TodoUtil

/**
 * Created by molszak.
 * 28.01.2018
 */
@BindingAdapter(value = ["priority"], requireAll = false)
fun bindPriority(view: ImageView, priority: Priority?) {
    val color = if (priority != null) {
        TodoUtil.getColorForPriority(view.context, priority)
    } else {
        ContextCompat.getColor(view.context, R.color.white)
    }
    view.setBackgroundColor(color)
}