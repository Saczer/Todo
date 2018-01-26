package pl.olszak.michal.todo.viewmodel

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * @author molszak
 *         created on 19.01.2018.
 */
abstract class BindingRecyclerAdapter : RecyclerView.Adapter<BindingViewHolder>() {

    var callbacks: BindingCallbacks? = null

    protected abstract fun getBindingForPosition(position: Int): Binding

    @LayoutRes
    protected abstract fun getLayoutForPosition(position: Int): Int

    override fun getItemViewType(position: Int): Int {
        return getLayoutForPosition(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater,
                viewType,
                parent,
                false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val binding = getBindingForPosition(position)
        holder.bind(binding, callbacks)
    }

}