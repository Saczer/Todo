package pl.olszak.michal.todo.cache.mapper

/**
 * @author molszak
 *         created on 29.01.2018.
 */
interface Converter<M, V> {

    fun convertTo(cached: M): V

    fun convertFrom(model: V): M

}