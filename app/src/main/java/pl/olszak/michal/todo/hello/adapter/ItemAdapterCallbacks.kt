package pl.olszak.michal.todo.hello.adapter

import android.view.View
import android.widget.Toast
import pl.olszak.michal.todo.data.model.Item

/**
 * @author molszak
 *         created on 26.01.2018.
 */
class ItemAdapterCallbacks constructor(private val itemAdapter: ItemAdapter) : ItemInteractionListener {

    override fun onItemClick(view: View, item: Item) {
        Toast.makeText(view.context, "Item clicked $item", Toast.LENGTH_LONG).show()
    }

}