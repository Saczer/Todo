package pl.olszak.michal.todo.tasks.navigation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.transition.Fade
import android.transition.TransitionInflater
import android.transition.TransitionSet
import android.view.View
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.settings.SettingsFragment
import pl.olszak.michal.todo.tasklist.TasksFragment
import pl.olszak.michal.todo.tasks.QuickCreateTaskFragment
import pl.olszak.michal.todo.tasks.TasksActivity
import pl.olszak.michal.todo.view.animation.model.RevealAnimationSetting
import javax.inject.Inject

/**
 * @author molszak
 *         created on 01.02.2018.
 */
class AndroidTasksNavigator @Inject constructor(private val activity: AppCompatActivity) : TasksNavigator {

    private val settingsFragment: SettingsFragment = SettingsFragment()
    private val tasksFragment: TasksFragment = TasksFragment()

    private var navigationCallback: TasksNavigator.NavigatorInteractionCallback? = null

    override fun restartSettingsChange() {
        activity.let {
            val intent = Intent(it, TasksActivity::class.java)
            intent.apply {
                putExtra(SETTINGS_CHANGE, true)
            }
            it.startActivity(intent)
            it.finish()
        }
    }

    override fun attach(navigationCallback: TasksNavigator.NavigatorInteractionCallback) {
        this.navigationCallback = navigationCallback
    }

    override fun detach(navigationCallback: TasksNavigator.NavigatorInteractionCallback) {
        if (this.navigationCallback == navigationCallback) {
            this.navigationCallback = null
        }
    }

    override fun toSettings() {
        activity.let {
            val transaction = it.supportFragmentManager.beginTransaction()
            val next = settingsFragment
            val previous = it.supportFragmentManager.findFragmentById(R.id.fragment_container)
            if (previous is SettingsFragment) {
                return
            }

            val enterTransitionSet = TransitionSet()
            enterTransitionSet.apply {
                addTransition(TransitionInflater.from(it).inflateTransition(android.R.transition.slide_right))
                addTransition(Fade().apply { duration = ENTER_FADE_TRANSITION_TIME })
                duration = DEFAULT_TRANSITION_TIME
            }

            next.enterTransition = enterTransitionSet
            transaction.replace(R.id.fragment_container, next)
                    .commit()

            navigationCallback?.hideAction(true)
        }
    }

    override fun toTaskList() {
        activity.let {
            val transaction = it.supportFragmentManager.beginTransaction()
            val next = tasksFragment
            val previous = it.supportFragmentManager.findFragmentById(R.id.fragment_container)
            if (previous is TasksFragment) {
                return
            }

            val enterTransitionSet = TransitionSet()
            enterTransitionSet.apply {
                addTransition(TransitionInflater.from(it).inflateTransition(android.R.transition.slide_left))
                addTransition(Fade().apply { duration = ENTER_FADE_TRANSITION_TIME })
                duration = DEFAULT_TRANSITION_TIME
            }

            next.enterTransition = enterTransitionSet
            transaction.replace(R.id.fragment_container, next)
                    .commit()

            navigationCallback?.hideAction(false)
        }
    }

    override fun toQuickCreateTask(view: View) {
        activity.let {
            val transaction = it.supportFragmentManager.beginTransaction()
            val previous = it.supportFragmentManager.findFragmentById(R.id.fragment_container)

            var revealAnimationSetting: RevealAnimationSetting? = null

            previous.view?.let {
                revealAnimationSetting = RevealAnimationSetting(
                        (view.x + view.width / 2).toInt(),
                        (view.y + view.height / 2).toInt(),
                        it.width,
                        it.height
                )
            }

            if (revealAnimationSetting != null) {
                val fragment: QuickCreateTaskFragment =
                        QuickCreateTaskFragment.newInstance(revealAnimationSetting!!)
                transaction.replace(R.id.outer_fragment_container, fragment)
                        .addToBackStack(fragment.javaClass.simpleName)
                        .commit()
            } else {
                val fragment = QuickCreateTaskFragment()
                transaction.replace(R.id.fragment_container, fragment).commit()
            }

            navigationCallback?.let {
                it.hideAction(true)
                it.hideNavigation(true)
            }
        }
    }

    override fun handleOnBackPressed(): Boolean {
        activity.let {
            val count = it.supportFragmentManager.backStackEntryCount
            return if (count == 0) {
                false
            } else {
                it.supportFragmentManager.popBackStack()
                returnFromCreateTask()
                true
            }
        }
    }

    override fun returnFromCreateTask() {
        navigationCallback?.let {
            it.hideAction(false)
            it.hideNavigation(false)
        }
    }

    companion object {
        private const val DEFAULT_TRANSITION_TIME = 200L
        private const val ENTER_FADE_TRANSITION_TIME = 220L
        const val SETTINGS_CHANGE = "settings_change_intent"
    }


}