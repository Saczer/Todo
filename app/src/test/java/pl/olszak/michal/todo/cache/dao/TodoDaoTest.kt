package pl.olszak.michal.todo.cache.dao

import android.arch.persistence.room.Room
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import pl.olszak.michal.todo.cache.TodoDatabase
import pl.olszak.michal.todo.cache.testutils.InstantTaskExecutorRule
import pl.olszak.michal.todo.cache.testutils.TaskFactory

/**
 * @author molszak
 *         created on 26.01.2018.
 */
@RunWith(RobolectricTestRunner::class)
open class TodoDaoTest {

    @JvmField
    @Rule
    val immediateExecutionRule = InstantTaskExecutorRule()

    lateinit var database: TodoDatabase

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
                RuntimeEnvironment.application.baseContext,
                TodoDatabase::class.java)
                .allowMainThreadQueries()
                .build()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun `insert and get user by id`() {
        val cachedTask = TaskFactory.createCachedTask()
        database.taskDao().insertCachedTask(cachedTask)

        val testObserver = database.taskDao()
                .getCachedTaskById(cachedTask.id)
                .test()

        testObserver.assertValue { task ->
            task.id == cachedTask.id && task.title == cachedTask.title
        }
    }

    @Test
    fun `inserting data saves database`() {
        database.taskDao().insertCachedTask(TaskFactory.createCachedTask())

        database.taskDao()
                .getAllCachedTasks()
                .test()
                .assertValue { tasks ->
                    tasks.isNotEmpty()
                }
    }

    @Test
    fun `get tasks retrieves data`() {
        val tasks = TaskFactory.createCachedTaskList(5)
        tasks.forEach {
            database.taskDao().insertCachedTask(it)
        }

        database.taskDao()
                .getAllCachedTasks()
                .test()
                .assertValue { cached ->
                    cached == tasks.sortedWith(compareBy({ it.id }, { it.id }))
                }
    }

    @Test
    fun `clearing database returns empty list`() {
        val tasks = TaskFactory.createCachedTaskList(4)
        tasks.forEach {
            database.taskDao().insertCachedTask(it)
        }

        database.taskDao()
                .clearAllCachedTasks()
        database.taskDao()
                .getAllCachedTasks()
                .test()
                .assertValue { it.isEmpty() }
    }

}