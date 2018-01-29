package pl.olszak.michal.todo.cache.testutils

import org.threeten.bp.Instant
import pl.olszak.michal.todo.cache.model.CachedTask
import pl.olszak.michal.todo.data.model.Icon
import pl.olszak.michal.todo.data.model.Priority
import pl.olszak.michal.todo.data.model.Task

/**
 * @author molszak
 *         created on 26.01.2018.
 */
class TaskFactory {

    companion object Factory {

        fun createCachedTask(): CachedTask {
            return CachedTask(
                    DataFactory.randomLong(),
                    "Random title",
                    "Some description",
                    false,
                    Icon.MEAL,
                    Priority.CRITICAL,
                    Instant.now()
            )
        }

        fun createCachedTaskList(size: Int): List<CachedTask> {
            return List(size, {
                createCachedTask()
            })
        }

        fun createTaskBinding(): Task {
            val binding = Task(DataFactory.randomLong())
            binding.title = "Some random title"
            binding.description = "Some description"
            binding.icon = Icon.MEAL
            binding.priority = Priority.CRITICAL
            binding.done = false
            binding.time = Instant.now()
            return binding
        }
    }
}