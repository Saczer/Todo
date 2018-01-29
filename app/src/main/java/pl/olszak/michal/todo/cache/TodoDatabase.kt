package pl.olszak.michal.todo.cache

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import pl.olszak.michal.todo.cache.dao.TaskDao
import pl.olszak.michal.todo.cache.model.CachedTask
import pl.olszak.michal.todo.cache.model.TodoRoomConverters

/**
 * @author molszak
 *         created on 25.01.2018.
 */
@Database(entities = [CachedTask::class], version = 1)
@TypeConverters(TodoRoomConverters::class)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

}