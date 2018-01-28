package pl.olszak.michal.todo.cache.testutils

import pl.olszak.michal.todo.cache.model.CachedTask

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
                    null,
                    false,
                    "icon_id",
                    0x0001,
                    System.currentTimeMillis()
            )
        }

        fun createCachedTaskList(size: Int): List<CachedTask> {
            return List(size, {
                createCachedTask()
            })
        }
    }
}