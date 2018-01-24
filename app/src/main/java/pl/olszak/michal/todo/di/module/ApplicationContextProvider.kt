package pl.olszak.michal.todo.di.module

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule
import pl.olszak.michal.todo.TodoApp

/**
 * @author molszak
 *         created on 24.01.2018.
 */
@Module(includes = [AndroidSupportInjectionModule::class])
abstract class ApplicationContextProvider {

    @Binds
    abstract fun application(app: TodoApp): Application

}