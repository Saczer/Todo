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
    abstract fun bindThreadExecutor(executor: TodoExecutor): ThreadExecutor

    @Binds
    abstract fun bindPostExecutionThread(postExecutionThread: UiThread): PostExecutionThread

    @Binds
    abstract fun bindTodoSchedulers(todoSchedulersFacade: TodoSchedulersFacade): TodoSchedulers

}