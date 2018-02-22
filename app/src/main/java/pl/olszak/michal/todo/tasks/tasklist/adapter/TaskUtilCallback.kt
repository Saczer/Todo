package pl.olszak.michal.todo.tasks.tasklist.adapter

import android.os.Bundle
import android.support.v7.util.DiffUtil
import pl.olszak.michal.todo.data.model.Task

/**
 * @author molszak
 *         created on 22.02.2018.
 */
class TaskUtilCallback constructor(private val oldList: List<Task>,
                                   private val newList: List<Task>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = oldList[newItemPosition]

        return old.title == new.title &&
                old.description == new.description &&
                old.priority == new.priority
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val old = oldList[oldItemPosition]
        val new = oldList[newItemPosition]

        val payload = Bundle()

        if(old.title != new.title){
            payload.putString(TITLE_CHANGE, new.title)
        }
        if(old.description != new.description){
            payload.putString(DESCRIPTION_CHANGE, new.description)
        }
        if(old.priority != new.priority){
            payload.putInt(PRIORITY_CHANGE, new.priority.id)
        }

        return if(payload.isEmpty){
            null
        }else {
            payload
        }
    }

    companion object {
        private const val TITLE_CHANGE = "title_change"
        private const val DESCRIPTION_CHANGE = "description_change"
        private const val PRIORITY_CHANGE = "priority_change"
    }
}