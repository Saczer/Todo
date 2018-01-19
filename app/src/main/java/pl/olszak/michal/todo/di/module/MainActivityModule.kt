package pl.olszak.michal.todo.di.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import pl.olszak.michal.todo.hello.MainViewModel
import pl.olszak.michal.todo.hello.MainViewModelImpl

/**
 * @author molszak
 *         created on 18.01.2018.
 */
@Module
class MainActivityModule {

    @Provides
    @Reusable
    fun provideViewModel(viewModel: MainViewModelImpl): MainViewModel = viewModel
}