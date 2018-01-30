package pl.olszak.michal.todo.hello

import android.databinding.BaseObservable
import android.databinding.Bindable
import pl.olszak.michal.todo.BR
import pl.olszak.michal.todo.data.model.Item
import pl.olszak.michal.todo.hello.adapter.ItemAdapter
import pl.olszak.michal.todo.viewmodel.BindingRecyclerAdapter

/**
 * @author molszak
 *         created on 25.01.2018.
 */
class MainViewObservable : BaseObservable() {

    private val adapter = ItemAdapter()

    internal fun setItems(items: List<Item>) {
        adapter.setItems(items)
        notifyPropertyChanged(BR.adapter)
    }

    @Bindable
    fun getAdapter(): BindingRecyclerAdapter {
        return adapter
    }
}