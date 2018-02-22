package pl.olszak.michal.todo.di.module

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import pl.olszak.michal.todo.cache.DatabaseConstants
import pl.olszak.michal.todo.cache.TodoDatabase
import pl.olszak.michal.todo.cache.dao.TaskDao
import pl.olszak.michal.todo.di.scope.PerApplication

/**
 * @author molszak
 *         created on 25.01.2018.
 */
@Module
class DatabaseModule {

    @Provides
    @PerApplication
    fun provideDatabase(application: Application): TodoDatabase {
        return Room.databaseBuilder(application,
                TodoDatabase::class.java,
                DatabaseConstants.DATABASE_NAME)
                .build()
    }

    @Provides
    fun provideTaskDao(database: TodoDatabase): TaskDao {
        return database.taskDao()
    }

}