package pl.olszak.michal.todo.navigation

import android.content.Intent
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v7.app.AppCompatActivity
import android.transition.Fade
import android.transition.TransitionInflater
import android.transition.TransitionSet
import android.view.View
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.createtask.QuickCreateTaskFragment
import pl.olszak.michal.todo.settings.SettingsFragment
import pl.olszak.michal.todo.tasklist.TasksFragment
import pl.olszak.michal.todo.tasks.TasksActivity
import javax.inject.Inject

/**
 * @author molszak
 *         created on 01.02.2018.
 */
class AndroidNavigator @Inject constructor(private val activity: AppCompatActivity?) : Navigator {

    private val settingsFragment: SettingsFragment = SettingsFragment()
    private val tasksFragment: TasksFragment = TasksFragment()

    override fun restartSettingsChange() {
        activity?.let {
            val intent = Intent(it, TasksActivity::class.java)
            intent.apply {
                putExtra(SETTINGS_CHANGE, true)
            }
            it.startActivity(intent)
            it.finish()
        }
    }

    override fun toSettings() {
        activity?.let {
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
        }
    }

    override fun toTaskList() {
        activity?.let {
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
        }
    }

    //Todo create reveal animation
    override fun toQuickCreateTask(view: View) {
        activity?.let {
            val transaction = it.supportFragmentManager.beginTransaction()
            val enterTransition = TransitionSet()
            enterTransition.apply {
                addTransition(TransitionInflater.from(it).inflateTransition(android.R.transition.explode)
                        .setInterpolator(FastOutSlowInInterpolator()))
            }

            val fragment = QuickCreateTaskFragment()
            fragment.enterTransition = enterTransition
            fragment.show(transaction, CREATE_TASK_FRAGMENT)
        }
    }

    companion object {
        private const val DEFAULT_TRANSITION_TIME = 200L
        private const val ENTER_FADE_TRANSITION_TIME = 220L
        const val SETTINGS_CHANGE = "settings_change_intent"

        const val CREATE_TASK_FRAGMENT = "create_task_fragment_tag"
    }


}