package pl.olszak.michal.todo.hello.adapter

import android.view.View
import pl.olszak.michal.todo.data.model.Item
import pl.olszak.michal.todo.viewmodel.BindingCallbacks

/**
 * @author molszak
 *         created on 30.01.2018.
 */
interface ItemInteractionListener : BindingCallbacks {

    fun onItemClick(view: View, item: Item)
}