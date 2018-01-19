package pl.olszak.michal.todo.viewmodel

import android.support.annotation.LayoutRes

/**
 * @author molszak
 *         created on 19.01.2018.
 */
abstract class SingleBindingRecyclerAdapter
constructor(@LayoutRes private val layoutResource: Int)
    : BindingRecyclerAdapter() {

    override fun getLayoutForPosition(position: Int): Int = layoutResource

}