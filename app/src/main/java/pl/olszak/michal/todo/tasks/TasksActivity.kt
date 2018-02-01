package pl.olszak.michal.todo.tasks

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.databinding.ActivityTasksBinding
import pl.olszak.michal.todo.di.FragmentInjectingActivity
import pl.olszak.michal.todo.navigation.AndroidNavigator
import pl.olszak.michal.todo.navigation.Navigator
import javax.inject.Inject

/**
 * @author molszak
 *         created on 30.01.2018.
 */
class TasksActivity : FragmentInjectingActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var navigator: Navigator

    private lateinit var tasksViewModel: TasksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        if (intent != null) {
            intent.extras?.let {
                if (it.getBoolean(AndroidNavigator.SHOULD_CHANGE_THEME)) {
                    setTheme(R.style.NormalTheme)
                }
            }
        }
        super.onCreate(savedInstanceState)
        val binding: ActivityTasksBinding = DataBindingUtil.setContentView(this, R.layout.activity_tasks)

        tasksViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TasksViewModel::class.java)
        tasksViewModel.setNavigator(navigator)

        if (savedInstanceState != null) {
            if (binding.bottomNavigation.selectedItemId == R.id.settings) {
                binding.fab.visibility = View.INVISIBLE
            }
        } else {
            navigator.navigateToTaskList()
        }
        binding.contract = tasksViewModel
    }

}