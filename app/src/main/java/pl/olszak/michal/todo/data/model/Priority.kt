package pl.olszak.michal.todo.data.model

/**
 * Created by molszak.
 * 27.01.2018
 */
enum class Priority constructor(val id: Int) {

    CRITICAL(1),
    HIGH(2),
    MEDIUM(3),
    LOW(4),
    NONE(0);

    companion object {
        fun getPriority(flag: Int): Priority {
            val priority = Priority.values().find {
                it.id == flag
            }
            return priority ?: LOW
        }
    }

}