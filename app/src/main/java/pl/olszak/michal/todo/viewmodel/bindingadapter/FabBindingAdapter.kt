package pl.olszak.michal.todo.viewmodel.bindingadapter

import android.databinding.BindingAdapter
import android.support.design.widget.FloatingActionButton

/**
 * @author molszak
 *         created on 01.02.2018.
 */

@BindingAdapter(value = ["android:visibility"], requireAll = false)
fun bindVisibility(view: FloatingActionButton, visibility: Boolean?) {
    visibility?.let {
        if (it) {
            view.show()
        } else {
            view.hide()
        }
    }
}