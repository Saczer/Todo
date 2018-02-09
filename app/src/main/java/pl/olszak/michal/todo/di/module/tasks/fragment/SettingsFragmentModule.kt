package pl.olszak.michal.todo.di.module.tasks.fragment

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.olszak.michal.todo.di.ViewModelKey
import pl.olszak.michal.todo.di.scope.PerFragment
import pl.olszak.michal.todo.tasks.settings.SettingsViewModel

/**
 * @author molszak
 *         created on 31.01.2018.
 */
@PerFragment
@Module
abstract class SettingsFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel

}