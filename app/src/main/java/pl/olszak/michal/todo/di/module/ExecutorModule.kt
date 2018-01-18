package pl.olszak.michal.todo.di.module

import dagger.Module
import dagger.Provides
import pl.olszak.michal.todo.domain.concurent.*
import javax.inject.Singleton

/**
 * @author molszak
 *         created on 18.01.2018.
 */
@Module
@Singleton
class ExecutorModule {

    @Provides
    fun provideThreadExecutor(executor: TodoExecutor): ThreadExecutor = executor

    @Provides
    fun providePostExecutionThread(postExecutionThread: UiThread): PostExecutionThread =
            postExecutionThread

    fun provideTodoSchedulers(todoSchedulersFacade: TodoSchedulersFacade): TodoSchedulers =
            todoSchedulersFacade
}