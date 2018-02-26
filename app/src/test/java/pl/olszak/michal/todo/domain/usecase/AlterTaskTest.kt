package pl.olszak.michal.todo.domain.usecase

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import pl.olszak.michal.todo.domain.concurrent.TestTodoSchedulers
import pl.olszak.michal.todo.testutils.TaskFactory
import pl.olszak.michal.todo.testutils.TaskStoreStubber
import pl.olszak.michal.todo.data.TaskStore
import pl.olszak.michal.todo.data.model.Task
import pl.olszak.michal.todo.domain.concurent.TodoSchedulers
import pl.olszak.michal.todo.domain.interactor.task.AlterTask

/**
 * @author molszak
 *         created on 29.01.2018.
 */
@RunWith(JUnit4::class)
class AlterTaskTest {

    private lateinit var useCase: AlterTask

    private lateinit var mockTaskStore: TaskStore
    private val todoSchedulers: TodoSchedulers = TestTodoSchedulers()
    private val task: Task = TaskFactory.createTaskBinding()

    @Before
    fun setup() {
        mockTaskStore = mock()
        useCase = AlterTask(mockTaskStore, todoSchedulers)
    }

    @Test
    fun `building use case calls store`() {
        useCase.buildUseCaseCompletable(task)
        verify(mockTaskStore).addTask(task)
    }

    @Test
    fun `adding null task returns error`() {
        val testObserver = useCase.execute(null).test()
        testObserver.assertError { it is NullPointerException }
    }

    @Test
    fun `building use case completes`() {
        TaskStoreStubber.stubAddTask(mockTaskStore, task)
        val testObserver = useCase.execute(task).test()
        testObserver.assertComplete()
    }
}