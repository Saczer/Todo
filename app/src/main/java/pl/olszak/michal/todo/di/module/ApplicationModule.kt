package pl.olszak.michal.todo.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import pl.olszak.michal.todo.TodoApp
import javax.inject.Singleton

/**
 * @author molszak
 *         created on 18.01.2018.
 */
@Singleton
@Module
class ApplicationModule {

    @Provides
    fun provideContext(application : TodoApp) : Context =  application


}