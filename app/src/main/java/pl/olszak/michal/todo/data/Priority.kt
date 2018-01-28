package pl.olszak.michal.todo.data

/**
 * Created by molszak.
 * 27.01.2018
 */
enum class Priority constructor(val flag: Int) {

    CRITICAL(0x1000),
    HIGH(0x0100),
    MEDIUM(0x0010),
    LOW(0x0001);

    companion object {
        fun getPriority(flag: Int): Priority {
            val priority = Priority.values().find {
                it.flag == flag
            }
            return priority ?: LOW
        }
    }

}