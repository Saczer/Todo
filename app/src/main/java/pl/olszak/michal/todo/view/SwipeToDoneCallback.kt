package pl.olszak.michal.todo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import pl.olszak.michal.todo.R

/**
 * Created by molszak.
 * 25.02.2018
 */
abstract class SwipeToDoneCallback(context: Context) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    private val checkIcon: Drawable
    private val intrinsicWidth: Int
    private val intrinsicHeight: Int
    private val backgroundColor = Color.WHITE
    private val background = ColorDrawable()

    private var buttonState: ButtonState = ButtonState.GONE

    init {
        background.apply {
            color = backgroundColor
        }

        val drawable = ContextCompat.getDrawable(context, R.drawable.check)
        val wrapped = DrawableCompat.wrap(drawable)
        DrawableCompat.setTint(wrapped, ContextCompat.getColor(context, R.color.green))
        checkIcon = wrapped
        intrinsicWidth = checkIcon.intrinsicWidth
        intrinsicHeight = checkIcon.intrinsicHeight
    }

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        return false
    }

    override fun onChildDraw(canvas: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        buttonState = if (dX < -intrinsicWidth) ButtonState.VISIBLE else ButtonState.GONE

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            if (buttonState == ButtonState.VISIBLE) {
                drawCheck(canvas, viewHolder, dX)
            }
        }
        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }


    private fun drawCheck(canvas: Canvas, viewHolder: RecyclerView.ViewHolder, dX: Float) {
        val itemView = viewHolder.itemView
        itemView.let {
            val itemHeight = it.bottom - it.top
            background.setBounds(it.right + dX.toInt(), it.top, it.right, it.bottom)
            background.draw(canvas)

            val checkMargin = (itemHeight - intrinsicHeight) / 2
            val checkTop = it.top + checkMargin
            val checkLeft = it.right - checkMargin - intrinsicWidth
            val checkRight = it.right - checkMargin
            val checkBottom = checkTop + intrinsicHeight

            checkIcon.setBounds(checkLeft, checkTop, checkRight, checkBottom)
            checkIcon.draw(canvas)
        }
    }

    private enum class ButtonState {
        GONE,
        VISIBLE
    }
}
