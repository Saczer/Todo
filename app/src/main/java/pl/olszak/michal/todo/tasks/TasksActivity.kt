package pl.olszak.michal.todo.tasks

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.databinding.ActivityTasksBinding
import pl.olszak.michal.todo.tasks.list.TasksFragment
import pl.olszak.michal.todo.tasks.settings.SettingsFragment
import javax.inject.Inject

/**
 * @author molszak
 *         created on 30.01.2018.
 */
class TasksActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var tasksViewModel: TasksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        val binding: ActivityTasksBinding = DataBindingUtil.setContentView(this, R.layout.activity_tasks)

        tasksViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TasksViewModel::class.java)

        if (savedInstanceState == null) {
            navigateToTasks()
        }

        binding.contract = tasksViewModel

        binding.fab.setOnClickListener({
            Snackbar.make(binding.fabContainer, R.string.settings, Snackbar.LENGTH_LONG).show()
        })

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.settings -> {
                    navigateToSettings()
                    binding.fab.hide()
                    true
                }
                R.id.task_list -> {
                    navigateToTasks()
                    binding.fab.show()
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateToSettings() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SettingsFragment())
                .addToBackStack(null)
                .commit()
    }

    private fun navigateToTasks() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TasksFragment())
                .addToBackStack(null)
                .commit()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }
}