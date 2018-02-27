package pl.olszak.michal.todo.di.module

import dagger.Binds
import dagger.Module
import pl.olszak.michal.todo.data.TaskStore
import pl.olszak.michal.todo.data.TaskStoreImpl
import pl.olszak.michal.todo.di.scope.PerApplication

/**
 * @author molszak
 *         created on 22.02.2018.
 */
@Module
abstract class StoreModule {

    @Binds
    @PerApplication
    abstract fun bindTaskStore(taskStore: TaskStoreImpl): TaskStore
}