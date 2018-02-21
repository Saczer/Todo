package pl.olszak.michal.todo.testutils

import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import pl.olszak.michal.todo.cache.dao.TaskDao
import pl.olszak.michal.todo.cache.model.CachedTask

/**
 * @author molszak
 *         created on 06.02.2018.
 */
class TaskDaoStubber {

    companion object Factory {

        fun stubGetAllCachedTasks(taskDao: TaskDao, cached: List<CachedTask>) {
            whenever(taskDao.getAllCachedTasks())
                    .thenReturn(Flowable.just(cached))
        }

        fun stubGetCachedTaskById(taskDao: TaskDao, cached: CachedTask) {
            cached.id?.let {
                whenever(taskDao.getCachedTaskById(it))
                        .thenReturn(Flowable.just(cached))
            }

        }

    }
}