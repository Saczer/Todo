package pl.olszak.michal.todo.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import pl.olszak.michal.todo.TodoApp
import pl.olszak.michal.todo.di.module.*
import pl.olszak.michal.todo.di.scope.PerApplication

/**
 * @author molszak
 *         created on 18.01.2018.
 */
@PerApplication
@Component(
        modules = [
            ApplicationModule::class,
            DatabaseModule::class,
            ViewModelModule::class,
            AndroidSupportInjectionModule::class,
            ActivityBindingModule::class,
            StoreModule::class,
            ExecutorModule::class
        ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: TodoApp)

}