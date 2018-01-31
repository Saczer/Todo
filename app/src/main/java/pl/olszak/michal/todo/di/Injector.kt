package pl.olszak.michal.todo.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import pl.olszak.michal.todo.TodoApp

/**
 * @author molszak
 *         created on 25.01.2018.
 */
class Injector {

    companion object {
        fun inject(todoApp: TodoApp) {

            val component = DaggerApplicationComponent
                    .builder()
                    .application(todoApp)
                    .build()
            component.inject(todoApp)

            todoApp.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                override fun onActivityPaused(activity: Activity?) {}

                override fun onActivityResumed(activity: Activity?) {}

                override fun onActivityStarted(activity: Activity?) {}

                override fun onActivityDestroyed(activity: Activity?) {}

                override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {}

                override fun onActivityStopped(activity: Activity?) {}

                override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
                    handleActivity(activity)
                }
            })
        }

        private fun handleActivity(activity: Activity) {
            if (activity is HasSupportFragmentInjector) {
                AndroidInjection.inject(activity)
            }
            (activity as? FragmentActivity)?.supportFragmentManager?.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
                override fun onFragmentCreated(fm: FragmentManager?, f: Fragment?, savedInstanceState: Bundle?) {
                    if (f is Injectable) {
                        AndroidSupportInjection.inject(f)
                    }
                }
            }, true)
        }
    }
}