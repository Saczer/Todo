package pl.olszak.michal.todo.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.olszak.michal.todo.di.ViewModelFactory
import pl.olszak.michal.todo.di.ViewModelKey
import pl.olszak.michal.todo.di.scope.PerActivity
import pl.olszak.michal.todo.hello.MainViewModel
import pl.olszak.michal.todo.settings.SettingsViewModel
import pl.olszak.michal.todo.tasks.TasksViewModel

/**
 * @author molszak
 *         created on 25.01.2018.
 */
@Module
@PerActivity
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TasksViewModel::class)
    abstract fun bindTasksViewModel(viewModel: TasksViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}