package pl.olszak.michal.todo.di.module.tasks

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.olszak.michal.todo.di.scope.PerActivity
import pl.olszak.michal.todo.tasks.list.TasksFragment
import pl.olszak.michal.todo.tasks.settings.SettingsFragment

/**
 * @author molszak
 *         created on 31.01.2018.
 */
@PerActivity
@Module
abstract class TasksActivityModule {

    @ContributesAndroidInjector(modules = [TasksFragmentModule::class])
    abstract fun tasksFragmentInjector(): TasksFragment

    @ContributesAndroidInjector(modules = [SettingsFragmentModule::class])
    abstract fun settingsFragmentInjector(): SettingsFragment

}