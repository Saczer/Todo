package pl.olszak.michal.todo.data.model

import org.threeten.bp.Instant
import pl.olszak.michal.todo.viewmodel.Binding

/**
 * Created by molszak.
 * 20.01.2018
 */
data class Task(var id: Long? = null,
                val title: String,
                val description: String = "",
                val done: Boolean = false,
                val priority: Priority = Priority.LOW,
                val repeating: Boolean = false,
                val time: Instant? = null) : Binding, Comparable<Task> {

    fun complete(): Task {
        return Task(id, title, description, true, priority, repeating, time)
    }

    fun uncomplete(): Task {
        return Task(id, title, description, false, priority, repeating, time)
    }

    override fun compareTo(other: Task): Int {
        if (other.done == this.done) {
            if (this.id != null && other.id != null) {
                this.id?.let { tId ->
                    other.id?.let { oId ->
                        return (tId - oId).toInt()
                    }
                }
            }
        }
        return if (other.done) -1 else 1
    }

}