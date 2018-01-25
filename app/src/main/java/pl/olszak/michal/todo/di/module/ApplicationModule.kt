package pl.olszak.michal.todo.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import pl.olszak.michal.todo.di.scope.PerApplication

/**
 * @author molszak
 *         created on 18.01.2018.
 */
@Module
@PerApplication
abstract class ApplicationModule {

    @Binds
    abstract fun provideContext(application: Application): Context


}