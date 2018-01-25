package pl.olszak.michal.todo.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import pl.olszak.michal.todo.TodoApp
import pl.olszak.michal.todo.di.module.*

/**
 * @author molszak
 *         created on 18.01.2018.
 */
@Component(
        modules = [
            ApplicationModule::class,
            DatabaseModule::class,
            ViewModelModule::class,
            AndroidSupportInjectionModule::class,
            ActivityBindingModule::class,
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