package pl.olszak.michal.todo.tasks.tasklist

import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.databinding.FragmentAllTasksBinding
import pl.olszak.michal.todo.di.Injectable
import pl.olszak.michal.todo.tasks.tasklist.adapter.TaskAdapter
import pl.olszak.michal.todo.util.viewModelProvider
import pl.olszak.michal.todo.view.SwipeToDoneCallback
import javax.inject.Inject

/**
 * @author molszak
 *         created on 31.01.2018.
 */
class TasksListFragment : Fragment(), Injectable {

    private lateinit var binding: FragmentAllTasksBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel: TasksListViewModel = viewModelProvider(viewModelFactory)
        binding.vm = viewModel
        viewModel.start()

        val swipeHandler = object : SwipeToDoneCallback(context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {

            }

        }

        binding.tasksList.let {
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            it.adapter = TaskAdapter()
            it.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            val itemTouchHelper = ItemTouchHelper(swipeHandler)
            itemTouchHelper.attachToRecyclerView(it)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_all_tasks,
                container,
                false)
        return binding.root
    }
}