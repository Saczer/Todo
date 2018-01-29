package pl.olszak.michal.todo.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.threeten.bp.Instant
import pl.olszak.michal.todo.cache.DatabaseConstants
import pl.olszak.michal.todo.data.model.Icon
import pl.olszak.michal.todo.data.model.Priority

/**
 * @author molszak
 *         created on 25.01.2018.
 */
@Entity(tableName = DatabaseConstants.CACHED_TASK_TABLE)
data class CachedTask(

        @PrimaryKey
        var id: Long,
        var title: String,
        var description: String? = null,
        var done: Boolean = false,
        var icon: Icon = Icon.NONE,
        var priority: Priority = Priority.LOW,
        var time: Instant? = null
)