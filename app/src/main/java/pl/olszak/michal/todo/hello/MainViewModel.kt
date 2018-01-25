package pl.olszak.michal.todo.hello

import pl.olszak.michal.todo.domain.model.Item
import pl.olszak.michal.todo.util.NameGenerator
import pl.olszak.michal.todo.viewmodel.TodoViewModel
import javax.inject.Inject

/**
 * @author molszak
 *         created on 19.01.2018.
 */
class MainViewModel @Inject constructor() : TodoViewModel<MainViewObservable>() {

    private val items: List<Item>
    private val observable = MainViewObservable()

    init {
        items = MutableList(20, { index ->
            Item(index, NameGenerator.getRandomName())
        })
        observable.setItems(items)
    }

    override fun provideBindable(): MainViewObservable {
        return observable
    }

}