package pl.olszak.michal.todo.di

import pl.olszak.michal.todo.TodoApp

/**
 * @author molszak
 *         created on 25.01.2018.
 */
class Injector {

    companion object {
        fun inject(todoApp: TodoApp) {

            val component = DaggerApplicationComponent
                    .builder()
                    .application(todoApp)
                    .build()
            component.inject(todoApp)
        }
    }
}