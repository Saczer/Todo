package pl.olszak.michal.todo.util

import java.util.*

/**
 * @author molszak
 *         created on 19.01.2018.
 */
object NameGenerator {

    private val list = listOf(
            "Michał",
            "Agata",
            "Marek",
            "Asia",
            "Karolina",
            "Natalia",
            "Maciej",
            "Darek",
            "Mateusz",
            "Dariusz"
    )

    fun getRandomName(): String {
        val position = Random().nextInt(list.size)
        return list[position]
    }

}