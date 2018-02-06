package pl.olszak.michal.todo.tasks

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.cache.dao.TodoPreferences
import pl.olszak.michal.todo.databinding.ActivityTasksBinding
import pl.olszak.michal.todo.di.FragmentInjectingActivity
import pl.olszak.michal.todo.navigation.AndroidNavigator
import pl.olszak.michal.todo.navigation.Navigator
import pl.olszak.michal.todo.util.TodoUtils
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
    @Inject
    lateinit var todoPreferences: TodoPreferences

    private lateinit var tasksViewModel: TasksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val themePalette = todoPreferences.getThemeColor()
        setTheme(TodoUtils.getStyle(themePalette))

        val binding: ActivityTasksBinding = DataBindingUtil.setContentView(this, R.layout.activity_tasks)

        tasksViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TasksViewModel::class.java)
        tasksViewModel.navigator = navigator

        //todo: this is violation of mvvm should be done somewhere else
        if (savedInstanceState != null) {
            if (binding.bottomNavigation.selectedItemId == R.id.settings) {
                tasksViewModel.visibility.set(false)
            }
        } else {
            val changedSettings = intent.getBooleanExtra(AndroidNavigator.SETTINGS_CHANGE, false)
            if (changedSettings) {
                navigator.toSettings()
                tasksViewModel.visibility.set(false)
                binding.bottomNavigation.selectedItemId = R.id.settings
            } else {
                navigator.toTaskList()
            }
        }

        binding.contract = tasksViewModel
    }

}