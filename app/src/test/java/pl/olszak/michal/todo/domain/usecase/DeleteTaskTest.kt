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
import pl.olszak.michal.todo.domain.interactor.task.DeleteTask

/**
 * @author molszak
 *         created on 06.02.2018.
 */
@RunWith(JUnit4::class)
class DeleteTaskTest {

    private lateinit var useCase: DeleteTask

    private lateinit var mockTaskStore: TaskStore
    private val todoSchedulers: TodoSchedulers = TestTodoSchedulers()
    private val task: Task = TaskFactory.createTaskBinding()

    @Before
    fun setup() {
        mockTaskStore = mock()
        useCase = DeleteTask(mockTaskStore, todoSchedulers)
    }

    @Test
    fun `building use case calls store`() {
        useCase.buildUseCaseCompletable(task)
        task.id?.let {
            verify(mockTaskStore).clearTaskWithId(it)
        }
    }

    @Test
    fun `executing empty use case returns exception`() {
        val testObserver = useCase.execute(null).test()
        testObserver.assertError { it is NullPointerException && it.message == "Task can't be null" }
    }

    @Test
    fun `executing valid use case completes`() {
        TaskStoreStubber.stubClearTask(mockTaskStore, task)
        val testObserver = useCase.execute(task).test()
        testObserver.assertComplete()
    }

}