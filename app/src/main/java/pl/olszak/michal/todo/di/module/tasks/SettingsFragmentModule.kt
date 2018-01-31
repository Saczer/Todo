package pl.olszak.michal.todo.di.module.tasks

import dagger.Module
import dagger.Provides
import pl.olszak.michal.todo.data.model.Item
import pl.olszak.michal.todo.di.scope.PerFragment

/**
 * @author molszak
 *         created on 31.01.2018.
 */
@PerFragment
@Module
abstract class SettingsFragmentModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideItem(): Item {
            return Item(1, "Yeye injected Item")
        }
    }

}