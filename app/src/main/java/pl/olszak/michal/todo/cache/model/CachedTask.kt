package pl.olszak.michal.todo.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.threeten.bp.Instant
import pl.olszak.michal.todo.cache.DatabaseConstants
import pl.olszak.michal.todo.data.model.Priority

/**
 * @author molszak
 *         created on 25.01.2018.
 */
@Entity(tableName = DatabaseConstants.CACHED_TASK_TABLE)
data class CachedTask(val title: String,
                      val description: String = "",
                      val done: Boolean = false,
                      val repeating: Boolean = false,
                      val priority: Priority = Priority.LOW,
                      val time: Instant? = null) : Comparable<CachedTask> {

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    override fun compareTo(other: CachedTask): Int {
        return (id!! - other.id!!).toInt()
    }
}