package pl.olszak.michal.todo.data.model

import org.threeten.bp.Instant
import pl.olszak.michal.todo.viewmodel.Binding

/**
 * Created by molszak.
 * 20.01.2018
 */
data class Task(

        val id: Long,
        val title: String,
        val description: String? = null,
        val done: Boolean = false,
        val priority: Priority = Priority.LOW,
        val repeating: Boolean = false,
        val time: Instant? = null

) : Binding