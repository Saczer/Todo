package pl.olszak.michal.todo.navigation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.transition.Fade
import android.transition.TransitionInflater
import android.transition.TransitionSet
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.settings.SettingsFragment
import pl.olszak.michal.todo.tasklist.TasksFragment
import pl.olszak.michal.todo.tasks.TasksActivity
import javax.inject.Inject

/**
 * @author molszak
 *         created on 01.02.2018.
 */
class AndroidNavigator @Inject constructor(private val activity: AppCompatActivity?) : Navigator {

    override fun changeTheme() {
        activity?.let {
            val intent = Intent(it, TasksActivity::class.java)
            intent.putExtra(SHOULD_CHANGE_THEME, true)
            it.startActivity(intent)
            it.finish()
        }
    }

    override fun navigateToSettings() {
        activity?.let {
            val transaction = it.supportFragmentManager.beginTransaction()
            val next = SettingsFragment()
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
        }
    }

    override fun navigateToTaskList() {
        activity?.let {
            val transaction = it.supportFragmentManager.beginTransaction()
            val next = TasksFragment()
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
        }
    }

    companion object {
        private const val DEFAULT_TRANSITION_TIME = 200L
        private const val ENTER_FADE_TRANSITION_TIME = 220L

        const val SHOULD_CHANGE_THEME = "should_change_theme"
    }


}