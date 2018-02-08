package pl.olszak.michal.todo.di.module.tasks

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.olszak.michal.todo.di.scope.PerActivity
import pl.olszak.michal.todo.tasks.QuickCreateTaskFragment

/**
 * @author molszak
 *         created on 08.02.2018.
 */
@Module
@PerActivity
abstract class CreateTaskActivityModule {

    @ContributesAndroidInjector
    abstract fun quickCreateFragmentInjector(): QuickCreateTaskFragment
}