package pl.olszak.michal.todo

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import pl.olszak.michal.todo.di.DaggerApplicationComponent
import timber.log.Timber
import javax.inject.Inject

/**
 * @author molszak
 *         created on 18.01.2018.
 */
class TodoApp : Application(), HasActivityInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        val component = DaggerApplicationComponent
                .builder()
                .application(this)
                .bind()
        component.inject(this)
        plantTree()
    }

    private fun plantTree() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return injector
    }
}