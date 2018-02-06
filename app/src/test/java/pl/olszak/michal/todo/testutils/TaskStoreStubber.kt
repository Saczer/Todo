package pl.olszak.michal.todo.testutils

import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import pl.olszak.michal.todo.data.TaskStore
import pl.olszak.michal.todo.data.model.Task

/**
 * @author molszak
 *         created on 06.02.2018.
 */
class TaskStoreStubber {

    companion object Factory {

        fun stubGetAllTasksWithList(taskStore: TaskStore, tasks: List<Task>) {
            whenever(taskStore.getAllTasks())
                    .thenReturn(Flowable.just(tasks))
        }

        fun stubGetAllTasks(taskStore: TaskStore) {
            whenever(taskStore.getAllTasks())
                    .thenReturn(Flowable.just(emptyList()))
        }

        fun stubClearTask(taskStore: TaskStore, task: Task) {
            whenever(taskStore.clearTaskWithId(task.id))
                    .thenReturn(Completable.complete())
        }

        fun stubAddTask(taskStore: TaskStore, task: Task) {
            whenever(taskStore.addTask(task))
                    .thenReturn(Completable.complete())
        }

        fun stubClearAllTasks(taskStore: TaskStore) {
            whenever(taskStore.clearAllTasks())
                    .thenReturn(Completable.complete())
        }
    }
}