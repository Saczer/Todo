package pl.olszak.michal.todo.tasks

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.databinding.FragmentQuickCreateTaskBinding
import pl.olszak.michal.todo.di.Injectable
import pl.olszak.michal.todo.view.animation.AnimationUtils
import pl.olszak.michal.todo.view.animation.model.RevealAnimationSetting

/**
 * @author molszak
 *         created on 06.02.2018.
 */
class QuickCreateTaskFragment : Fragment(), Injectable {

    companion object {
        private const val ARG_REVEAL_SETTINGS = "arg_reveal_settings"

        fun newInstance(revealSettings: RevealAnimationSetting): QuickCreateTaskFragment {
            val fragment = QuickCreateTaskFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_REVEAL_SETTINGS, revealSettings)

            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var binding: FragmentQuickCreateTaskBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_quick_create_task,
                container,
                false)

        val revealAnimationSetting: RevealAnimationSetting? = arguments.getParcelable(ARG_REVEAL_SETTINGS)
        revealAnimationSetting?.let {
            AnimationUtils.registerCircularRevealAnimation(context, binding.root, it)
        }

        return binding.root
    }


}