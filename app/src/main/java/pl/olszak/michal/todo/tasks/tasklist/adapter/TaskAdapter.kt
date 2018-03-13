package pl.olszak.michal.todo.tasks.tasklist.adapter

import android.support.v7.util.DiffUtil
import android.view.View
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.data.model.Task
import pl.olszak.michal.todo.viewmodel.SingleBindingRecyclerAdapter
import java.util.*

/**
 * @author molszak
 *         created on 22.02.2018.
 */
class TaskAdapter : SingleBindingRecyclerAdapter<Task>(R.layout.task_item) {

    private val tasks: MutableList<Task> = mutableListOf()

    init {
        setHasStableIds(true)
    }

    fun setItems(tasks: List<Task>) {
        val old = this.tasks.toList()
        val diffResult = DiffUtil.calculateDiff(TaskUtilCallback(old, tasks))
        diffResult.dispatchUpdatesTo(this)

        this.tasks.clear()
        this.tasks.addAll(tasks)
    }

    override fun getItemId(position: Int): Long {
        return tasks[position].id ?: 0L
    }

    override fun getBindingForPosition(position: Int): Task = tasks[position]

    override fun getItemCount(): Int = tasks.size

    fun removeItem(position: Int) {
        tasks.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onViewHolderBound(itemView: View, binding: Task, position: Int) {
        itemView.setOnClickListener {
            binding.taskOptionsVisible = !binding.taskOptionsVisible
            notifyItemChanged(position)
        }
    }

}