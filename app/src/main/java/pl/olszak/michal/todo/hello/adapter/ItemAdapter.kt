package pl.olszak.michal.todo.hello.adapter

import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.domain.model.Item
import pl.olszak.michal.todo.viewmodel.Binding
import pl.olszak.michal.todo.viewmodel.SingleBindingRecyclerAdapter

/**
 * @author molszak
 *         created on 19.01.2018.
 */
class ItemAdapter : SingleBindingRecyclerAdapter(R.layout.holder_test) {

    private val items = ArrayList<Item>()

    fun setItems(items: List<Item>) {
        callbacks = ItemAdapterCallbacks(this)
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getBindingForPosition(position: Int): Binding = items[position]

    override fun getItemCount(): Int = items.size
}