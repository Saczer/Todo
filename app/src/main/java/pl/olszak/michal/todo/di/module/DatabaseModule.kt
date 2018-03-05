package pl.olszak.michal.todo.di.module

import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
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
        return Room.databaseBuilder(application, TodoDatabase::class.java, DatabaseConstants.DATABASE_NAME)
                .addMigrations(MIGRATION_1_2)
                .build()
    }

    @Provides
    fun provideTaskDao(database: TodoDatabase): TaskDao {
        return database.taskDao()
    }

    companion object {
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("UPDATE ${DatabaseConstants.CACHED_TASK_TABLE} SET description = '' WHERE description IS NULL")
                database.execSQL("CREATE TABLE tasks_new (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " title TEXT NOT NULL," +
                        " description TEXT NOT NULL DEFAULT ''," +
                        " done INTEGER NOT NULL DEFAULT 0," +
                        " repeating INTEGER NOT NULL DEFAULT 0," +
                        " priority INTEGER NOT NULL DEFAULT 0," +
                        " time INTEGER)")
                database.execSQL("INSERT INTO tasks_new (id, title, description, done, repeating, priority, time) SELECT * FROM ${DatabaseConstants.CACHED_TASK_TABLE}")
                database.execSQL("DROP TABLE ${DatabaseConstants.CACHED_TASK_TABLE}")
                database.execSQL("ALTER TABLE tasks_new RENAME TO ${DatabaseConstants.CACHED_TASK_TABLE}")
            }

        }


    }

}