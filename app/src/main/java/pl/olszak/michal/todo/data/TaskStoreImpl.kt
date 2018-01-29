package pl.olszak.michal.todo.data

import io.reactivex.Completable
import io.reactivex.Flowable
import pl.olszak.michal.todo.cache.TodoDatabase
import pl.olszak.michal.todo.cache.mapper.TaskConverter
import pl.olszak.michal.todo.data.model.Task
import javax.inject.Inject

/**
 * @author molszak
 *         created on 29.01.2018.
 */
class TaskStoreImpl @Inject constructor(private val database: TodoDatabase,
                                        private val converter: TaskConverter) : TaskStore {

    override fun getAllTasks(): Flowable<List<Task>> {
        return Flowable.defer {
            database.taskDao().getAllCachedTasks()
        }.map {
                    it.map {
                        converter.convertTo(it)
                    }
                }
    }

    override fun getTaskWithId(id: Long): Flowable<Task> {
        return Flowable.defer {
            database.taskDao().getCachedTaskById(id)
        }.map {
                    converter.convertTo(it)
                }
    }

    override fun clearAllTasks(): Completable {
        return Completable.defer {
            database.taskDao().clearAllCachedTasks()
            Completable.complete()
        }
    }

    override fun clearTaskWithId(id: Long): Completable {
        return Completable.defer {
            database.taskDao().clearCachedTaskWithId(id)
            Completable.complete()
        }
    }

    override fun addTask(task: Task): Completable {
        return Completable.defer {
            database.taskDao().insertCachedTask(converter.convertFrom(task))
            Completable.complete()
        }
    }
}