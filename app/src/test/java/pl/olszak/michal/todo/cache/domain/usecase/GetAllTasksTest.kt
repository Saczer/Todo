package pl.olszak.michal.todo.cache.domain.usecase

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import pl.olszak.michal.todo.cache.domain.concurrent.TestTodoSchedulers
import pl.olszak.michal.todo.cache.testutils.TaskFactory
import pl.olszak.michal.todo.cache.testutils.TaskStoreStubber
import pl.olszak.michal.todo.data.TaskStore
import pl.olszak.michal.todo.domain.concurent.TodoSchedulers
import pl.olszak.michal.todo.domain.interactor.task.GetAllTasks

/**
 * @author molszak
 *         created on 06.02.2018.
 */
@RunWith(JUnit4::class)
class GetAllTasksTest {

    private lateinit var useCase: GetAllTasks

    private lateinit var mockTaskStore: TaskStore
    private val todoSchedulers: TodoSchedulers = TestTodoSchedulers()
    private val tasks = TaskFactory.createTaskBindingList(TASKS_SIZE)

    @Before
    fun setup() {
        mockTaskStore = mock()
        useCase = GetAllTasks(mockTaskStore, todoSchedulers)
    }

    @Test
    fun `building use case calls store`() {
        useCase.buildUseCase(null)
        verify(mockTaskStore).getAllTasks()
    }

    @Test
    fun `executing use case returns list when it exists`() {
        TaskStoreStubber.stubGetAllTasksWithList(mockTaskStore, tasks)
        val testObserver = useCase.execute(null).test()
        testObserver.assertValue { it.size == TASKS_SIZE }
    }

    @Test
    fun `executing use case doesn't return when empty`() {
        TaskStoreStubber.stubGetAllTasks(mockTaskStore)
        val testObserver = useCase.execute(null).test()
        testObserver.assertValue { it.isEmpty() }
    }

    companion object {
        private const val TASKS_SIZE = 5
    }

}