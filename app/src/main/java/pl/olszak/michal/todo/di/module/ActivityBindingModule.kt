package pl.olszak.michal.todo.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.olszak.michal.todo.MainActivity

/**
 * @author molszak
 *         created on 18.01.2018.
 */
@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun provideMainActivity(): MainActivity
}