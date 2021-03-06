package pl.olszak.michal.todo.di.module.tasks

import android.support.v7.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.android.ContributesAndroidInjector
import pl.olszak.michal.todo.di.module.tasks.fragment.QuickCreateTaskFragmentModule
import pl.olszak.michal.todo.di.module.tasks.fragment.SettingsFragmentModule
import pl.olszak.michal.todo.di.module.tasks.fragment.TasksListFragmentModule
import pl.olszak.michal.todo.di.scope.PerActivity
import pl.olszak.michal.todo.tasks.TasksActivity
import pl.olszak.michal.todo.tasks.create.QuickCreateTaskFragment
import pl.olszak.michal.todo.tasks.navigation.AndroidTasksNavigator
import pl.olszak.michal.todo.tasks.navigation.TasksNavigator
import pl.olszak.michal.todo.tasks.settings.SettingsFragment
import pl.olszak.michal.todo.tasks.tasklist.TasksListFragment

/**
 * @author molszak
 *         created on 31.01.2018.
 */

@Module
@PerActivity
abstract class TasksActivityModule {

    @ContributesAndroidInjector(modules = [TasksListFragmentModule::class])
    abstract fun tasksFragmentInjector(): TasksListFragment

    @ContributesAndroidInjector(modules = [SettingsFragmentModule::class])
    abstract fun settingsFragmentInjector(): SettingsFragment

    @ContributesAndroidInjector(modules = [QuickCreateTaskFragmentModule::class])
    abstract fun quickCreateFragmentInjector(): QuickCreateTaskFragment

    @Binds
    @Reusable
    abstract fun bindNavigator(navigator: AndroidTasksNavigator): TasksNavigator

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideActivity(activity: TasksActivity): AppCompatActivity {
            return activity
        }
    }

}