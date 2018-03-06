package pl.olszak.michal.todo.data

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import pl.olszak.michal.todo.cache.converter.TaskConverter
import pl.olszak.michal.todo.cache.dao.TaskDao
import pl.olszak.michal.todo.cache.model.CachedTask
import pl.olszak.michal.todo.testutils.TaskDaoStubber
import pl.olszak.michal.todo.testutils.TaskFactory
import pl.olszak.michal.todo.testutils.concurrent.ImmediateExecutionRule
import kotlin.test.fail

/**
 * @author molszak
 *         created on 06.02.2018.
 */
@RunWith(JUnit4::class)
open class TaskStoreTest {

    @JvmField
    @Rule
    val immediateExecutionRule = ImmediateExecutionRule()

    private lateinit var taskStore: TaskStoreImpl

    private lateinit var taskDao: TaskDao
    private val taskConverter: TaskConverter = TaskConverter()

    private val cachedTasks: List<CachedTask> = TaskFactory.createCachedTaskList(SIZE)

    @Before
    fun setup() {
        taskDao = mock()

        taskStore = TaskStoreImpl(taskDao, taskConverter)
    }

    @Test
    fun `getting tasks calls taskDao method`() {
        taskStore.getAllTasks().test()
        verify(taskDao).getAllCachedTasks()
    }

    @Test
    fun `getting tasks returns list of tasks`() {
        TaskDaoStubber.stubGetAllCachedTasks(taskDao, cachedTasks)
        val testObserver = taskStore.getAllTasks().test()
        testObserver.assertValue { it.size == cachedTasks.size }
                .assertValue {
                    it.zip(cachedTasks.sorted())
                            .none { it.first.id != it.second.id }
                }
    }

    @Test
    fun `getting task calls method`() {
        val cached = cachedTasks.first()
        cached.id?.let {
            taskStore.getTaskById(it).test()
            verify(taskDao).getCachedTaskById(it)
        }
        if(cached.id == null){
            fail("Id should not be null")
        }
    }

    @Test
    fun `getting tasks returns converted task`() {
        val cached = cachedTasks.first()
        TaskDaoStubber.stubGetCachedTaskById(taskDao, cached)
        cached.id?.let {
            val testObserver = taskStore.getTaskById(it).test()
            testObserver.assertValue { it.id == cached.id }
        }
        if(cached.id == null){
            fail("Id should not be null")
        }
    }

    @Test
    fun `clearing tasks calls method`() {
        taskStore.clearAllTasks().test()
        verify(taskDao).clearAllCachedTasks()
    }

    @Test
    fun `clearing task with id calls method`() {
        val cached = cachedTasks.first()
        cached.id?.let {
            taskStore.clearTaskWithId(it).test()
            verify(taskDao).clearCachedTaskWithId(it)
        }
        if(cached.id == null){
            fail("Id should not be null")
        }
    }

    @Test
    fun `add task calls method`() {
        val task = TaskFactory.createTaskBinding()
        val cached = taskConverter.convertFrom(task)
        taskStore.addTask(task).test()
        verify(taskDao).insertCachedTask(cached)
    }

    companion object {
        private const val SIZE = 5
    }


}