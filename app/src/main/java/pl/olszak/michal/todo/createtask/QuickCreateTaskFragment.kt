package pl.olszak.michal.todo.createtask

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.databinding.FragmentQuickCreateTaskBinding
import pl.olszak.michal.todo.di.Injectable
import pl.olszak.michal.todo.util.animation.model.RevealAnimationSetting

/**
 * @author molszak
 *         created on 06.02.2018.
 */
class QuickCreateTaskFragment : DialogFragment(), Injectable {

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

        return binding.root
    }
}