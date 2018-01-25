package pl.olszak.michal.todo.cache

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import pl.olszak.michal.todo.cache.dao.TaskDao
import pl.olszak.michal.todo.cache.model.CachedTask
import javax.inject.Inject

/**
 * @author molszak
 *         created on 25.01.2018.
 */
@Database(entities = [CachedTask::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

}