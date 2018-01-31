package pl.olszak.michal.todo.tasklist

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.databinding.FragmentTasksBinding
import pl.olszak.michal.todo.di.Injectable

/**
 * @author molszak
 *         created on 31.01.2018.
 */
class TasksFragment : Fragment(), Injectable {

    private lateinit var binding: FragmentTasksBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_tasks,
                container,
                false)

        return binding.root
    }
}