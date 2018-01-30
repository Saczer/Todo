package pl.olszak.michal.todo.view

import android.content.Context
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

/**
 * @author molszak
 *         created on 30.01.2018.
 */
class TaskRecyclerView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    init {
        val layoutManager = LinearLayoutManager(context, VERTICAL, false)
        setLayoutManager(layoutManager)
        addItemDecoration(DividerItemDecoration(context, VERTICAL))
    }

}