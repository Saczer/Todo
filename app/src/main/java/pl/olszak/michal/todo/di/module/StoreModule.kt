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
@PerApplication
abstract class StoreModule {

    @Binds
    abstract fun bindTaskStore(taskStore: TaskStoreImpl): TaskStore
}