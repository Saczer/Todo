package pl.olszak.michal.todo.di.module.tasks.fragment

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.olszak.michal.todo.di.ViewModelKey
import pl.olszak.michal.todo.di.scope.PerFragment
import pl.olszak.michal.todo.tasks.tasklist.TasksListViewModel

/**
 * @author molszak
 *         created on 31.01.2018.
 */
@PerFragment
@Module
abstract class TasksListFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(TasksListViewModel::class)
    abstract fun bindsTasksListViewModle(viewModel: TasksListViewModel): ViewModel

}