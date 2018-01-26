package pl.olszak.michal.todo.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import pl.olszak.michal.todo.cache.DatabaseConstants

/**
 * @author molszak
 *         created on 25.01.2018.
 */
@Entity(tableName = DatabaseConstants.CACHED_TASK_TABLE)
data class CachedTask(

        @PrimaryKey
        var id: Long,
        val title: String,
        val description: String,
        var parentId: Long? = null,
        var done: Boolean,
        val iconId: String,
        val priority: String,
        val date: Long

)