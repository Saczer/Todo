package pl.olszak.michal.todo.data.model

import pl.olszak.michal.todo.viewmodel.Binding

/**
 * @author molszak
 *         created on 19.01.2018.
 */
data class Item(

        val id: Int,
        val name: String

) : Binding