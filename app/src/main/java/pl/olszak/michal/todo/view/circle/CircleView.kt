package pl.olszak.michal.todo.view.circle

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.view.View
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.data.model.Priority
import pl.olszak.michal.todo.util.TodoUtils

/**
 * @author molszak
 *         created on 30.01.2018.
 */
class CircleView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var circleColor: Int = Color.WHITE

    init {
        initializeParameters(attrs)
    }

    private fun initializeParameters(attrs: AttributeSet?) {
        initializeAttributes(attrs)
        circlePaint.apply {
            color = circleColor
        }
    }

    private fun initializeAttributes(attrs: AttributeSet?) {
        attrs?.let {
            val attributeArray: TypedArray = context.obtainStyledAttributes(it, R.styleable.CircleView)
            circleColor = attributeArray.getInt(R.styleable.CircleView_cv_color,
                    Color.WHITE)

            attributeArray.recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawCircle(width / 2f, height / 2f, width / 2f, circlePaint)
    }

    fun setColor(@ColorInt color: Int) {
        circleColor = color
        circlePaint.apply {
            this.color = circleColor
        }
        invalidate()
    }

    @ColorInt
    fun getColor(): Int {
        return circleColor
    }

    fun setPriority(priority: Priority) {
        val priorityColor = TodoUtils.getColorForPriority(context, priority)
        setColor(priorityColor)
    }
}