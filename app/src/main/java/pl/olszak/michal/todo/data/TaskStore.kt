package pl.olszak.michal.todo.data

import io.reactivex.Completable
import io.reactivex.Flowable
import pl.olszak.michal.todo.data.model.Task

/**
 * @author molszak
 *         created on 29.01.2018.
 */
interface TaskStore {

    fun getAllTasks(): Flowable<List<Task>>

    fun getTaskWithId(id: Long): Flowable<Task>

    fun clearAllTasks(): Completable

    fun clearTaskWithId(id: Long): Completable

    fun addTask(task: Task) : Completable

}