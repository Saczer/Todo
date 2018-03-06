package pl.olszak.michal.todo.tasks.tasklist

import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.databinding.FragmentTaskListBinding
import pl.olszak.michal.todo.di.Injectable
import pl.olszak.michal.todo.tasks.tasklist.adapter.TaskAdapter
import pl.olszak.michal.todo.util.viewModelProvider
import pl.olszak.michal.todo.view.TaskSwipeCallback
import javax.inject.Inject

/**
 * @author molszak
 *         created on 31.01.2018.
 */
class TasksListFragment : Fragment(), Injectable {

    private lateinit var binding: FragmentTaskListBinding
    private lateinit var viewModel: TasksListViewModel
    private val adapter: TaskAdapter = TaskAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val snackbarCallback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(p0: Observable?, p1: Int) {
            if (viewModel.snackbarMessage.get() != 0) {
                Snackbar.make(binding.root, viewModel.snackbarMessage.get(), Snackbar.LENGTH_LONG).show()
                viewModel.snackbarMessage.set(0)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)
        binding.vm = viewModel
        viewModel.start()
        viewModel.snackbarMessage.addOnPropertyChangedCallback(snackbarCallback)

        val swipeHandler = object : TaskSwipeCallback(context) {

            override fun getSwipeDirs(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
                val position = viewHolder.adapterPosition
                val task = adapter.getBindingForPosition(position)
                if (task.done) {
                    return 0
                }
                return super.getSwipeDirs(recyclerView, viewHolder)
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val task = adapter.getBindingForPosition(position)
                adapter.removeItem(position)
                viewModel.completeTask(task)
            }
        }

        binding.toolbar.inflateMenu(R.menu.task_list)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.clear_completed -> {
                    viewModel.clearAllCompletedTasks()
                    true
                }
                else -> false
            }
        }

        binding.tasksList.let {
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            it.adapter = adapter
            it.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            val itemTouchHelper = ItemTouchHelper(swipeHandler)
            itemTouchHelper.attachToRecyclerView(it)
        }
    }

    override fun onDestroy() {
        viewModel.snackbarMessage.removeOnPropertyChangedCallback(snackbarCallback)
        super.onDestroy()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_task_list,
                container,
                false)

        binding.toolbar
        return binding.root
    }
}