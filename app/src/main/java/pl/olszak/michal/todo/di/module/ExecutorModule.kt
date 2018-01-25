package pl.olszak.michal.todo.di.module

import dagger.Binds
import dagger.Module
import pl.olszak.michal.todo.di.scope.PerApplication
import pl.olszak.michal.todo.domain.concurent.*

/**
 * @author molszak
 *         created on 18.01.2018.
 */
@Module
@PerApplication
abstract class ExecutorModule {

    @Binds
    abstract fun provideThreadExecutor(executor: TodoExecutor): ThreadExecutor

    @Binds
    abstract fun providePostExecutionThread(postExecutionThread: UiThread): PostExecutionThread

    @Binds
    abstract fun provideTodoSchedulers(todoSchedulersFacade: TodoSchedulersFacade): TodoSchedulers

}