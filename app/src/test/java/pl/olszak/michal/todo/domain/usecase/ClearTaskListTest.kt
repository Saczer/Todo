package pl.olszak.michal.todo.domain.usecase

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import pl.olszak.michal.todo.domain.concurrent.TestTodoSchedulers
import pl.olszak.michal.todo.testutils.TaskStoreStubber
import pl.olszak.michal.todo.data.TaskStore
import pl.olszak.michal.todo.domain.concurent.TodoSchedulers
import pl.olszak.michal.todo.domain.interactor.task.ClearTaskList

/**
 * @author molszak
 *         created on 06.02.2018.
 */
@RunWith(JUnit4::class)
class ClearTaskListTest {

    private lateinit var useCase: ClearTaskList

    private lateinit var mockTaskStore: TaskStore
    private val todoSchedulers: TodoSchedulers = TestTodoSchedulers()

    @Before
    fun setup() {
        mockTaskStore = mock()
        useCase = ClearTaskList(mockTaskStore, todoSchedulers)
    }

    @Test
    fun `building use case calls store`() {
        useCase.buildUseCaseCompletable(null)
        verify(mockTaskStore).clearAllTasks()
    }

    @Test
    fun `executing use case completes`() {
        TaskStoreStubber.stubClearAllTasks(mockTaskStore)
        val testObserver = useCase.execute(null).test()
        testObserver.assertComplete()
    }


}