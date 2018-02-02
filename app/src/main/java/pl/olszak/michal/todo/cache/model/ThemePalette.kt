package pl.olszak.michal.todo.cache.model

/**
 * @author molszak
 *         created on 02.02.2018.
 */
enum class ThemePalette {
    PINK,
    ORANGE,
    BLACK;

    companion object {
        fun getThemeByName(name: String): ThemePalette {
            val color = ThemePalette.values().find { it.name == name }
            return color ?: PINK
        }
    }

}