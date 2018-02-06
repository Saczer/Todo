package pl.olszak.michal.todo.cache.mapper

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import pl.olszak.michal.todo.cache.converter.TaskConverter
import pl.olszak.michal.todo.cache.model.CachedTask
import pl.olszak.michal.todo.testutils.TaskFactory
import pl.olszak.michal.todo.data.model.Task
import kotlin.test.assertEquals

/**
 * @author molszak
 *         created on 29.01.2018.
 */
@RunWith(JUnit4::class)
open class TaskConverterTest {

    private lateinit var converter: TaskConverter

    @Before
    fun setUp() {
        converter = TaskConverter()
    }

    @Test
    fun `mapping to cached maps data`() {
        val binding = TaskFactory.createTaskBinding()
        val cached = converter.convertFrom(binding)
        assertTaskEquality(binding, cached)
    }

    @Test
    fun `mapping from cached maps data`() {
        val cached = TaskFactory.createCachedTask()
        val binding = converter.convertTo(cached)
        assertTaskEquality(binding, cached)
    }

    private fun assertTaskEquality(binding: Task, cached: CachedTask) {
        assertEquals(binding.id, cached.id)
        assertEquals(binding.title, cached.title)
        assertEquals(binding.description, cached.description)
        assertEquals(binding.repeating, cached.repeating)
        assertEquals(binding.priority, cached.priority)
        assertEquals(binding.time, cached.time)
    }

}