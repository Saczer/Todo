package pl.olszak.michal.todo.domain.usecase

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import pl.olszak.michal.todo.data.TaskStore
import pl.olszak.michal.todo.domain.concurent.TodoSchedulers
import pl.olszak.michal.todo.domain.concurrent.TestTodoSchedulers
import pl.olszak.michal.todo.domain.interactor.task.ClearAllCompletedTasks
import pl.olszak.michal.todo.testutils.TaskStoreStubber

/**
 * @author molszak
 *         created on 26.02.2018.
 */
@RunWith(JUnit4::class)
class ClearAllCompletedTasksTest {

    private lateinit var useCase: ClearAllCompletedTasks

    private lateinit var mockTaskStore: TaskStore
    private val todoSchedulers: TodoSchedulers = TestTodoSchedulers()

    @Before
    fun setup() {
        mockTaskStore = mock()
        useCase = ClearAllCompletedTasks(mockTaskStore, todoSchedulers)
    }

    @Test
    fun `building use case calls store`() {
        useCase.buildUseCaseCompletable()
        verify(mockTaskStore).clearAllCompleted()
    }

    @Test
    fun `executing use case completes`() {
        TaskStoreStubber.stubClearAllCompletedTasks(mockTaskStore)
        val testObserver = useCase.execute().test()
        testObserver.assertComplete()
    }

}