package pl.olszak.michal.todo.tasks

import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.cache.dao.TodoPreferences
import pl.olszak.michal.todo.databinding.ActivityTasksBinding
import pl.olszak.michal.todo.di.FragmentInjectingActivity
import pl.olszak.michal.todo.tasks.navigation.TasksNavigator
import pl.olszak.michal.todo.util.tools.TodoUtils
import pl.olszak.michal.todo.util.viewModelProvider
import javax.inject.Inject

/**
 * @author molszak
 *         created on 30.01.2018.
 */
class TasksActivity : FragmentInjectingActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var navigator: TasksNavigator
    @Inject
    lateinit var todoPreferences: TodoPreferences

    private lateinit var viewModel: TasksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val themePalette = todoPreferences.getThemeColor()
        setTheme(TodoUtils.getStyle(themePalette))
        val binding: ActivityTasksBinding = DataBindingUtil.setContentView(this, R.layout.activity_tasks)
        viewModel = viewModelProvider(viewModelFactory)

        navigator.onRestoreInstanceState(savedInstanceState)
        viewModel.navigator = navigator
        binding.vm = viewModel

        val changedSettings = intent.getBooleanExtra(RestartOptions.SETTINGS_CHANGE, false)
        if (changedSettings) {
            viewModel.handleChangeSettings()
            intent.removeExtra(RestartOptions.SETTINGS_CHANGE)
            binding.bottomNavigation.selectedItemId = R.id.settings
        } else if (savedInstanceState == null) {
            viewModel.handleNoSavedState()
        }
    }

    override fun onBackPressed() {
        if (!viewModel.handleOnBackPressed()) {
            super.onBackPressed()
        }
    }
}