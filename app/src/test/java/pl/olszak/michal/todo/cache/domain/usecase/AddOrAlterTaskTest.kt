package pl.olszak.michal.todo.cache.domain.usecase

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import pl.olszak.michal.todo.cache.domain.concurrent.TestTodoSchedulers
import pl.olszak.michal.todo.cache.testutils.TaskFactory
import pl.olszak.michal.todo.data.TaskStore
import pl.olszak.michal.todo.data.model.Task
import pl.olszak.michal.todo.domain.concurent.TodoSchedulers
import pl.olszak.michal.todo.domain.interactor.task.AddOrAlterTask

/**
 * @author molszak
 *         created on 29.01.2018.
 */
@RunWith(JUnit4::class)
open class AddOrAlterTaskTest {

    private lateinit var useCase: AddOrAlterTask

    private lateinit var mockTaskStore: TaskStore
    private val todoSchedulers: TodoSchedulers = TestTodoSchedulers()
    private val task: Task = TaskFactory.createTaskBinding()

    @Before
    fun setup() {
        mockTaskStore = mock()
        useCase = AddOrAlterTask(mockTaskStore, todoSchedulers)
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
        stubTaskStoreAddTask(task)
        val testObserver = useCase.execute(task).test()
        testObserver.assertComplete()
    }

    private fun stubTaskStoreAddTask(task: Task) {
        whenever(mockTaskStore.addTask(task))
                .thenReturn(Completable.complete())
    }
}