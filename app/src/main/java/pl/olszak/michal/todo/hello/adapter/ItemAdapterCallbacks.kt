package pl.olszak.michal.todo.hello.adapter

import android.view.View
import android.widget.Toast
import pl.olszak.michal.todo.domain.model.Item
import pl.olszak.michal.todo.viewmodel.BindingCallbacks

/**
 * @author molszak
 *         created on 26.01.2018.
 */
class ItemAdapterCallbacks constructor(private val itemAdapter: ItemAdapter) : BindingCallbacks {

    fun onItemClick(view: View, item: Item) {
        Toast.makeText(view.context, "Item clicked $item", Toast.LENGTH_LONG).show()
    }

}