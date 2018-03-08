package pl.olszak.michal.todo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import pl.olszak.michal.todo.R

/**
 * Created by molszak.
 * 25.02.2018
 */
//todo: create second and third action for the swipe helper depending on the task state
abstract class TaskSwipeCallback(context: Context) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    private val checkIcon: Drawable = ContextCompat.getDrawable(context, R.drawable.check)
    private val trashIcon: Drawable = ContextCompat.getDrawable(context, R.drawable.delete)
    private val intrinsicWidth: Int
    private val intrinsicHeight: Int
    private val greenBackground = ColorDrawable()
    private val redBackground = ColorDrawable()

    private var rightButtonState: ButtonState = ButtonState.GONE
    private var leftButtonState: ButtonState = ButtonState.GONE

    init {
        val greenBackgroundColor = ContextCompat.getColor(context, R.color.green)
        val redBackgroundColor = ContextCompat.getColor(context, R.color.red)
        greenBackground.apply {
            color = greenBackgroundColor
        }
        redBackground.apply {
            color = redBackgroundColor
        }
        intrinsicWidth = checkIcon.intrinsicWidth
        intrinsicHeight = checkIcon.intrinsicHeight
    }

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        return false
    }

    override fun onChildDraw(canvas: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        rightButtonState = if (dX < -intrinsicWidth) ButtonState.VISIBLE else ButtonState.GONE
        leftButtonState = if (dX > intrinsicWidth) ButtonState.VISIBLE else ButtonState.GONE

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            if (rightButtonState == ButtonState.VISIBLE) {
                drawCheck(canvas, viewHolder, dX)
            }
            if (leftButtonState == ButtonState.VISIBLE) {
                drawTrashcan(canvas, viewHolder, dX)
            }
        }
        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun drawTrashcan(canvas: Canvas, viewHolder: RecyclerView.ViewHolder, dX: Float) {
        val itemView = viewHolder.itemView
        itemView.let {
            val itemHeight = it.bottom - it.top
            redBackground.setBounds(it.left, it.top, dX.toInt(), it.bottom)
            redBackground.draw(canvas)

            val trashMargin = (itemHeight - intrinsicHeight) / 2
            val trashTop = it.top + trashMargin
            val trashLeft = it.left + trashMargin
            val trashRight = trashLeft + intrinsicWidth
            val trashBottom = trashTop + intrinsicHeight

            trashIcon.setBounds(trashLeft, trashTop, trashRight, trashBottom)
            trashIcon.draw(canvas)
        }
    }


    private fun drawCheck(canvas: Canvas, viewHolder: RecyclerView.ViewHolder, dX: Float) {
        val itemView = viewHolder.itemView
        itemView.let {
            val itemHeight = it.bottom - it.top
            greenBackground.setBounds(it.right + dX.toInt(), it.top, it.right, it.bottom)
            greenBackground.draw(canvas)

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
