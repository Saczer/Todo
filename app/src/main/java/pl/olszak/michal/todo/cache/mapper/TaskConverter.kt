package pl.olszak.michal.todo.cache.mapper

import pl.olszak.michal.todo.cache.model.CachedTask
import pl.olszak.michal.todo.data.model.Task
import javax.inject.Inject

/**
 * @author molszak
 *         created on 29.01.2018.
 */
class TaskConverter @Inject constructor(): Converter<CachedTask, Task> {

    override fun convertTo(cached: CachedTask): Task {
        val binding = Task(cached.id)
        binding.title = cached.title
        binding.description = cached.description
        binding.priority = cached.priority
        binding.icon = cached.icon
        binding.time = cached.time
        binding.done = cached.done
        return binding
    }

    override fun convertFrom(model: Task): CachedTask {
        return CachedTask(
                model.id,
                model.title,
                model.description,
                model.done,
                model.icon,
                model.priority,
                model.time
        )
    }
}