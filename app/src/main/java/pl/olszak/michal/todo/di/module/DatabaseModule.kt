package pl.olszak.michal.todo.di.module

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import pl.olszak.michal.todo.cache.DatabaseConstants
import pl.olszak.michal.todo.cache.TodoDatabase
import pl.olszak.michal.todo.cache.dao.TaskDao
import pl.olszak.michal.todo.data.TaskStore
import pl.olszak.michal.todo.data.TaskStoreImpl
import pl.olszak.michal.todo.di.scope.PerApplication

/**
 * @author molszak
 *         created on 25.01.2018.
 */
@Module
@PerApplication
abstract class DatabaseModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideDatabase(application: Application): TodoDatabase {
            return Room.databaseBuilder(application,
                    TodoDatabase::class.java,
                    DatabaseConstants.DATABASE_NAME)
                    .build()
        }

        @Provides
        @JvmStatic
        fun provideTaskDao(database: TodoDatabase): TaskDao {
            return database.taskDao()
        }
    }

    @Binds
    abstract fun bindTaskStore(taskStore: TaskStoreImpl): TaskStore

}