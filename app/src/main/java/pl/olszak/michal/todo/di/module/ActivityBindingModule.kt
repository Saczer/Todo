package pl.olszak.michal.todo.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.olszak.michal.todo.di.module.tasks.TasksActivityModule
import pl.olszak.michal.todo.di.scope.PerActivity
import pl.olszak.michal.todo.hello.MainActivity
import pl.olszak.michal.todo.tasks.TasksActivity

/**
 * @author molszak
 *         created on 18.01.2018.
 */
@Module
@PerActivity
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [TasksActivityModule::class])
    abstract fun provideTasksActivity(): TasksActivity

}