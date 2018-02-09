package pl.olszak.michal.todo.tasks

import android.databinding.DataBindingUtil
import android.os.Bundle
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.cache.dao.TodoPreferences
import pl.olszak.michal.todo.databinding.ActivityCreateTaskBinding
import pl.olszak.michal.todo.di.FragmentInjectingActivity
import pl.olszak.michal.todo.util.tools.TodoUtils
import javax.inject.Inject

/**
 * @author molszak
 *         created on 08.02.2018.
 */
class CreateTaskActivity : FragmentInjectingActivity() {

    @Inject
    lateinit var todoPreferences: TodoPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val themePalette = todoPreferences.getThemeColor()
        setTheme(TodoUtils.getStyle(themePalette))

        val binding: ActivityCreateTaskBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_create_task)
    }
}