package pl.olszak.michal.todo.settings

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.data.model.Item
import pl.olszak.michal.todo.databinding.FragmentSettingsBinding
import pl.olszak.michal.todo.di.Injectable
import javax.inject.Inject

/**
 * @author molszak
 *         created on 31.01.2018.
 */
class SettingsFragment : Fragment(), Injectable {

    lateinit var binding: FragmentSettingsBinding

    @Inject
    lateinit var item: Item

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        binding.item = item
        return binding.root
    }
}