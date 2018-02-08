package pl.olszak.michal.todo.tasks

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.cache.dao.TodoPreferences
import pl.olszak.michal.todo.databinding.ActivityCreateTaskBinding
import pl.olszak.michal.todo.di.FragmentInjectingActivity
import pl.olszak.michal.todo.util.tools.TodoUtils
import pl.olszak.michal.todo.view.animation.model.RevealAnimationSetting
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

        val binding: ActivityCreateTaskBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_task)
        val revealSettings: RevealAnimationSetting? = intent.getParcelableExtra(ARG_REVEAL_SETTINGS)
        revealSettings?.let {
            val transaction = supportFragmentManager.beginTransaction()
            val fragment: QuickCreateTaskFragment = QuickCreateTaskFragment.newInstance(it)
            transaction.replace(R.id.fragment_container, fragment)
                    .commit()
        }
    }

    companion object {
        private const val ARG_REVEAL_SETTINGS = "arg_reveal_settings"

        fun createIntentWith(context: Context,
                             revealAnimationSetting: RevealAnimationSetting): Intent {
            return Intent(context, CreateTaskActivity::class.java).apply {
                putExtra(ARG_REVEAL_SETTINGS, revealAnimationSetting)
            }
        }
    }
}