package pl.olszak.michal.todo.cache.converter

import android.arch.persistence.room.TypeConverter
import org.threeten.bp.Instant
import pl.olszak.michal.todo.data.model.Priority

/**
 * @author molszak
 *         created on 29.01.2018.
 */
class TodoRoomConverters {

    @TypeConverter
    fun toPriority(priority: Int): Priority {
        return Priority.getPriority(priority)
    }

    @TypeConverter
    fun fromPriority(priority: Priority): Int {
        return priority.id
    }

    @TypeConverter
    fun toInstant(timestamp: Long?): Instant? {
        return timestamp?.let {
            Instant.ofEpochMilli(timestamp)
        }
    }

    @TypeConverter
    fun toTimestamp(instant: Instant?): Long? {
        return instant?.toEpochMilli()
    }

}