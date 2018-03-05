package pl.olszak.michal.todo.data

import io.reactivex.Completable
import io.reactivex.Flowable
import pl.olszak.michal.todo.cache.converter.TaskConverter
import pl.olszak.michal.todo.cache.dao.TaskDao
import pl.olszak.michal.todo.data.model.Task
import javax.inject.Inject

/**
 * @author molszak
 *         created on 29.01.2018.
 */
class TaskStoreImpl @Inject constructor(private val taskDao: TaskDao,
                                        private val converter: TaskConverter) : TaskStore {

    /*todo: the sorting operation should be done somewhere else, for e.g add parameter according
    *       to which category it could be sorted, ideally the use case could provide comparator
    *       object to which store could obey
    */
    override fun getAllTasks(): Flowable<List<Task>> {
        return Flowable.defer {
            taskDao.getAllCachedTasks()
        }.map {
            it.map {
                converter.convertTo(it)
            }.sorted()
        }
    }

    override fun getTaskById(id: Long): Flowable<Task> {
        return Flowable.defer {
            taskDao.getCachedTaskById(id)
        }.map {
            converter.convertTo(it)
        }
    }

    override fun clearAllTasks(): Completable {
        return Completable.defer {
            taskDao.clearAllCachedTasks()
            Completable.complete()
        }
    }

    override fun clearTaskWithId(id: Long): Completable {
        return Completable.defer {
            taskDao.clearCachedTaskWithId(id)
            Completable.complete()
        }
    }

    override fun clearAllCompleted(): Completable {
        return Completable.defer {
            taskDao.clearCompletedTasks()
            Completable.complete()
        }
    }

    override fun addTask(task: Task): Completable {
        return Completable.defer {
            taskDao.insertCachedTask(converter.convertFrom(task))
            Completable.complete()
        }
    }
}