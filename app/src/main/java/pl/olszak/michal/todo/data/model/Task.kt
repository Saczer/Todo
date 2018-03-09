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
                val time: Instant? = null) : Binding {

    fun mark(complete : Boolean): Task {
        return Task(id, title, description, complete, priority, repeating, time)
    }

    class TaskDoneComparator : Comparator<Task> {
        override fun compare(first: Task, second: Task): Int {
            if (second.done == first.done) {
                if (first.id != null && second.id != null) {
                    first.id?.let { tId ->
                        second.id?.let { oId ->
                            return (tId - oId).toInt()
                        }
                    }
                }
            }
            return if (second.done) -1 else 1
        }
    }

    class TaskDoneReverseComparator : Comparator<Task> {
        override fun compare(first: Task, second: Task): Int {
            if (second.done == first.done) {
                if (first.id != null && second.id != null) {
                    first.id?.let { tId ->
                        second.id?.let { oId ->
                            return (tId - oId).toInt()
                        }
                    }
                }
            }
            return if (second.done) 1 else -1
        }

    }
}