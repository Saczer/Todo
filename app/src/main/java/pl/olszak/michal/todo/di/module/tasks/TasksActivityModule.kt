package pl.olszak.michal.todo.di.module.tasks

import android.support.v7.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import pl.olszak.michal.todo.di.scope.PerActivity
import pl.olszak.michal.todo.navigation.AndroidNavigator
import pl.olszak.michal.todo.navigation.Navigator
import pl.olszak.michal.todo.settings.SettingsFragment
import pl.olszak.michal.todo.tasklist.TasksFragment
import pl.olszak.michal.todo.tasks.TasksActivity

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

    @Binds
    abstract fun bindNavigator(navigator: AndroidNavigator): Navigator

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideActivity(activity: TasksActivity): AppCompatActivity {
            return activity
        }
    }

}