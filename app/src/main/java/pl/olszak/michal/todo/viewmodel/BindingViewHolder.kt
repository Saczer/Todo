package pl.olszak.michal.todo.viewmodel

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import pl.olszak.michal.todo.BR

/**
 * @author molszak
 *         created on 19.01.2018.
 */
class BindingViewHolder constructor(private val binding: ViewDataBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(obj: Binding, callbacks: BindingCallbacks? = null) {
        binding.setVariable(BR.obj, obj)
        callbacks?.let {
            binding.setVariable(BR.callbacks, it)
        }
        binding.executePendingBindings()
    }

}