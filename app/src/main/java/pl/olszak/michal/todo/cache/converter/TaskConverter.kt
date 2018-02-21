package pl.olszak.michal.todo.cache.converter

import pl.olszak.michal.todo.cache.model.CachedTask
import pl.olszak.michal.todo.data.model.Task
import javax.inject.Inject

/**
 * @author molszak
 *         created on 29.01.2018.
 */
class TaskConverter @Inject constructor() : Converter<CachedTask, Task> {

    override fun convertTo(cached: CachedTask): Task {
        return Task(cached.id,
                cached.title,
                cached.description,
                cached.done,
                cached.priority,
                cached.repeating,
                cached.time)
    }

    override fun convertFrom(model: Task): CachedTask {
        return CachedTask(
                model.title,
                model.description,
                model.done,
                model.repeating,
                model.priority,
                model.time).apply {
            model.id?.let {
                this.id = it
            }
        }
    }
}