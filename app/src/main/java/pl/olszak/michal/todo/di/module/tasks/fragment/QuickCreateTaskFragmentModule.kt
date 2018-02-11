package pl.olszak.michal.todo.di.module.tasks.fragment

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.olszak.michal.todo.di.ViewModelKey
import pl.olszak.michal.todo.di.scope.PerFragment
import pl.olszak.michal.todo.tasks.create.QuickCreateTaskViewModel

/**
 * @author molszak
 *         created on 09.02.2018.
 */
@Module
@PerFragment
abstract class QuickCreateTaskFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(QuickCreateTaskViewModel::class)
    abstract fun bindCreateTaskViewModel(viewModel: QuickCreateTaskViewModel): ViewModel

}