package pl.olszak.michal.todo.hello

import pl.olszak.michal.todo.viewmodel.BindingRecyclerAdapter
import pl.olszak.michal.todo.viewmodel.ViewModel

/**
 * @author molszak
 *         created on 19.01.2018.
 */
interface MainViewModel : ViewModel {

    fun getAdapter(): BindingRecyclerAdapter

}