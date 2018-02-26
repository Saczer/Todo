package pl.olszak.michal.todo.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import pl.olszak.michal.todo.cache.DatabaseConstants
import pl.olszak.michal.todo.cache.model.CachedTask

/**
 * @author molszak
 *         created on 25.01.2018.
 */
@Dao
abstract class TaskDao {

    @Query(DatabaseConstants.SELECT_ALL_TASKS)
    abstract fun getAllCachedTasks(): Flowable<List<CachedTask>>

    @Query(DatabaseConstants.SELECT_TASK_BY_ID)
    abstract fun getCachedTaskById(id: Long): Flowable<CachedTask>

    @Query(DatabaseConstants.DELETE_TASK_BY_ID)
    abstract fun clearCachedTaskWithId(id: Long)

    @Query(DatabaseConstants.DELETE_ALL_TASKS)
    abstract fun clearAllCachedTasks()

    @Query(DatabaseConstants.DELETE_ALL_COMPLETED_TASKS)
    abstract fun clearCompletedTasks()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCachedTask(cachedTask: CachedTask)

}

