package pl.olszak.michal.todo.hello

import android.databinding.BaseObservable
import android.databinding.Bindable
import pl.olszak.michal.todo.BR
import pl.olszak.michal.todo.hello.adapter.ItemAdapter
import pl.olszak.michal.todo.model.Item
import pl.olszak.michal.todo.util.NameGenerator
import pl.olszak.michal.todo.viewmodel.BindingRecyclerAdapter
import javax.inject.Inject

/**
 * @author molszak
 *         created on 19.01.2018.
 */
class MainViewModelImpl @Inject constructor() : MainViewModel, BaseObservable() {

    private var adapter = ItemAdapter()
    private val items: List<Item>

    init {
        items = MutableList(20, {index ->
            Item(index, NameGenerator.getRandomName())
        })
        adapter.setItems(items)
        notifyPropertyChanged(BR.adapter)
    }

    fun setAdapter(adapter : ItemAdapter){
        this.adapter = adapter
        notifyPropertyChanged(BR.adapter)
    }

    @Bindable
    override fun getAdapter(): BindingRecyclerAdapter = adapter

    override fun clear() {
    }
}