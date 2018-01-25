package pl.olszak.michal.todo.cache

/**
 * @author molszak
 *         created on 25.01.2018.
 */
object DatabaseConstants {

    const val CACHED_TASK_TABLE = "tasks"

    const val SELECT_ALL_TASKS = "SELECT * FROM $CACHED_TASK_TABLE"

    const val SELECT_TASK_BY_ID = "SELECT * FROM $CACHED_TASK_TABLE WHERE id = :id"

    const val DELETE_ALL_TASKS = "DELETE FROM $CACHED_TASK_TABLE"

    const val DATABASE_NAME = "todos.db"
}