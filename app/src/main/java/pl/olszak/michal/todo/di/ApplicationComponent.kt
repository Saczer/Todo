package pl.olszak.michal.todo.di

import dagger.Component
import dagger.android.AndroidInjector
import pl.olszak.michal.todo.TodoApp
import pl.olszak.michal.todo.di.module.ActivityBindingModule
import pl.olszak.michal.todo.di.module.ApplicationContextProvider
import pl.olszak.michal.todo.di.module.ApplicationModule
import pl.olszak.michal.todo.di.module.ExecutorModule

/**
 * @author molszak
 *         created on 18.01.2018.
 */
@Component(
        modules = [
            ApplicationContextProvider::class,
            ApplicationModule::class,
            ActivityBindingModule::class,
            ExecutorModule::class
        ]
)
interface ApplicationComponent : AndroidInjector<TodoApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TodoApp>()

}