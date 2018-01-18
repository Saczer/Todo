package pl.olszak.michal.todo.mapper

/**
 * @author molszak
 *         created on 18.01.2018.
 */
interface Converter<out V, in M> {

    fun convert(model: M): V

}