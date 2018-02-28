package pl.olszak.michal.todo.tasks.create

import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.databinding.FragmentQuickTaskBinding
import pl.olszak.michal.todo.di.Injectable
import pl.olszak.michal.todo.tasks.navigation.TasksNavigator
import pl.olszak.michal.todo.util.extension.showSoftInputMethod
import pl.olszak.michal.todo.util.viewModelProvider
import pl.olszak.michal.todo.view.animation.TodoAnimationUtils
import pl.olszak.michal.todo.view.animation.model.RevealAnimationSetting
import javax.inject.Inject

/**
 * @author molszak
 *         created on 06.02.2018.
 */
class QuickCreateTaskFragment : Fragment(), Injectable {

    private lateinit var binding: FragmentQuickTaskBinding
    private var alreadyAnimated: Boolean = false

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel: QuickCreateTaskViewModel = viewModelProvider(viewModelFactory)
        binding.vm = viewModel
        binding.enterTitle.showSoftInputMethod()

        savedInstanceState?.let {
            alreadyAnimated = it.getBoolean(SAVED_ANIMATION_STATE, false)
        }

        if (!alreadyAnimated) {
            alreadyAnimated = true
            val revealAnimationSetting: RevealAnimationSetting? = arguments.getParcelable(ARG_REVEAL_SETTINGS)
            revealAnimationSetting?.let {
                val endColor = ContextCompat.getColor(context, R.color.blackOpacity54)
                TodoAnimationUtils.registerCircularRevealAnimation(context, binding.root, it, endColor)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_quick_task,
                container,
                false)

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean(SAVED_ANIMATION_STATE, alreadyAnimated)
    }

    companion object {
        private const val SAVED_ANIMATION_STATE = "saved_animation_state"
        private const val ARG_REVEAL_SETTINGS = "arg_reveal_settings"

        fun createWithReveal(revealSettings: RevealAnimationSetting): QuickCreateTaskFragment {
            val fragment = QuickCreateTaskFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_REVEAL_SETTINGS, revealSettings)

            fragment.arguments = bundle
            return fragment
        }
    }
}