package pl.olszak.michal.todo.viewmodel.bindingadapter

import android.databinding.BindingAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import pl.olszak.michal.todo.viewmodel.BindingRecyclerAdapter

/**
 * @author molszak
 *         created on 19.01.2018.
 */
@BindingAdapter(value = ["adapter"], requireAll = false)
fun bindAdapter(view: RecyclerView, adapter: BindingRecyclerAdapter?) {
    if (adapter != null) {
        view.adapter = adapter
        view.layoutManager = LinearLayoutManager(view.context)
    }
}
