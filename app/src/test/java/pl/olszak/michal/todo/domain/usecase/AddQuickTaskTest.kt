package pl.olszak.michal.todo.domain.usecase

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import pl.olszak.michal.todo.data.TaskStore
import pl.olszak.michal.todo.data.model.Task
import pl.olszak.michal.todo.domain.concurent.TodoSchedulers
import pl.olszak.michal.todo.domain.concurrent.TestTodoSchedulers
import pl.olszak.michal.todo.domain.interactor.task.AddQuickTask
import pl.olszak.michal.todo.testutils.TaskStoreStubber

/**
 * @author molszak
 *         created on 26.02.2018.
 */
@RunWith(JUnit4::class)
class AddQuickTaskTest {

    private lateinit var useCase: AddQuickTask

    private lateinit var mockTaskStore: TaskStore
    private val todoSchedulers: TodoSchedulers = TestTodoSchedulers()

    @Before
    fun setup() {
        mockTaskStore = mock()
        useCase = AddQuickTask(mockTaskStore, todoSchedulers)
    }

    @Test
    fun `building use case calls store`() {
        useCase.buildUseCaseCompletable(TITLE)
        verify(mockTaskStore).addTask(Task(title = TITLE))
    }

    @Test
    fun `adding null task returns error`() {
        val testObserver = useCase.execute().test()
        testObserver.assertError { it is NullPointerException }
    }

    @Test
    fun `building use case completes`() {
        val task = Task(title = TITLE)
        TaskStoreStubber.stubAddTask(mockTaskStore, task)
        val testObserver = useCase.execute(TITLE).test()
        testObserver.assertComplete()
    }

    companion object {
        private const val TITLE = "something"
    }

}