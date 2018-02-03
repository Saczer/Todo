package pl.olszak.michal.todo.view.circle

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.util.calculateBounds

/**
 * @author molszak
 *         created on 30.01.2018.
 */
class CircleView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var circleColor: Int = Color.WHITE

    private val circleRect = RectF()
    private var circleRadius = 0f

    init {
        initializeParameters(attrs)
    }

    private fun initializeParameters(attrs: AttributeSet?) {
        initializeAttributes(attrs)
        circlePaint.apply {
            color = circleColor
        }
        setup()
    }

    private fun initializeAttributes(attrs: AttributeSet?) {
        attrs?.let {
            val attributeArray: TypedArray = context.obtainStyledAttributes(it, R.styleable.CircleView)
            circleColor = attributeArray.getInt(R.styleable.CircleView_cv_color,
                    Color.WHITE)

            attributeArray.recycle()
        }
    }

    private fun setup() {
        if (width == 0 && height == 0) {
            return
        }

        circleRect.set(calculateBounds(this))
        circleRadius = Math.min(circleRect.height() / 2f, circleRect.width() / 2f)
        invalidate()
    }

    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        super.setPadding(left, top, right, bottom)
        setup()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setup()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawCircle(circleRect.centerX(), circleRect.centerY(), circleRadius, circlePaint)
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

    private inner class OutlineProvider : ViewOutlineProvider() {
        override fun getOutline(view: View?, outline: Outline?) {
            val bounds = Rect()
            circleRect.roundOut(bounds)
            outline?.setRoundRect(bounds, bounds.width() / 2f)
        }

    }

}