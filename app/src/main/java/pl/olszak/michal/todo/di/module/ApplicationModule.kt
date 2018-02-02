package pl.olszak.michal.todo.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import pl.olszak.michal.todo.cache.TodoPreferencesImpl
import pl.olszak.michal.todo.cache.dao.TodoPreferences
import pl.olszak.michal.todo.di.scope.PerApplication

/**
 * @author molszak
 *         created on 18.01.2018.
 */
@Module
@PerApplication
abstract class ApplicationModule {

    @Binds
    abstract fun bindContext(application: Application): Context

    @Binds
    abstract fun bindTodoPreferences(todoPreferencesImpl: TodoPreferencesImpl): TodoPreferences
}