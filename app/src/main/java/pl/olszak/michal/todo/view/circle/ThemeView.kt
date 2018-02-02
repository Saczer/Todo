package pl.olszak.michal.todo.view.circle

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.util.TodoUtils

/**
 * @author molszak
 *         created on 02.02.2018.
 */
class ThemeView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val FACTOR = .8f
        private const val STROKE = 6f
    }

    private val circlePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val outlineCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    @ColorInt
    private var circleColor: Int = Color.WHITE
    private val checkedDrawable: Drawable
    private var state: ThemeView.State = State.SELECTED
    private val drawableGravity = Gravity.CENTER
    private var drawableBoundsSet: Boolean = false

    enum class State {

        IDLE {
            override fun change(): State = SELECTED
        },
        SELECTED {
            override fun change(): State = IDLE
        };

        abstract fun change(): State
    }

    init {
        initializeAttributes(attrs)
        checkedDrawable = CheckedDrawable(context)
        circlePaint.apply {
            color = circleColor
        }
        outlineCirclePaint.apply {
            color = TodoUtils.darkenColor(circleColor, FACTOR)
            strokeWidth = STROKE
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
        }
    }

    private fun initializeAttributes(attrs: AttributeSet?) {
        attrs?.let {
            val attributeArray: TypedArray = context.obtainStyledAttributes(it, R.styleable.ThemeView)
            circleColor = attributeArray.getInt(R.styleable.ThemeView_tv_color, Color.WHITE)
            attributeArray.recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            if (state == State.SELECTED) {
                it.drawCircle(width / 2f, height / 2f, width / 2f - STROKE / 2f, circlePaint)
                if (!drawableBoundsSet) {
                    layoutCheckedDrawable()
                }
                checkedDrawable.draw(it)
                it.drawCircle(width / 2f, height / 2f, width / 2f - STROKE / 2f, outlineCirclePaint)
            } else {
                it.drawCircle(width / 2f, height / 2f, width / 2f, circlePaint)
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        layoutCheckedDrawable()
    }

    private fun layoutCheckedDrawable() {
        val drawableBounds = checkedDrawable.bounds
        Gravity.apply(drawableGravity,
                checkedDrawable.intrinsicWidth,
                checkedDrawable.intrinsicHeight,
                Rect(0, 0, width, height),
                0,
                0,
                drawableBounds)
        checkedDrawable.bounds = drawableBounds
        drawableBoundsSet = true
    }

    fun setColor(@ColorInt color: Int) {
        circleColor = color
        invalidate()
    }

    fun changeState() {
        state = state.change()
        invalidate()
    }

    fun getState(): State {
        return state
    }

    private class CheckedDrawable internal constructor(context: Context) : Drawable() {
        private val bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.ic_check_white_24dp)
        private val paint: Paint = Paint()

        override fun getIntrinsicWidth(): Int {
            return bitmap.height
        }

        override fun getIntrinsicHeight(): Int {
            return bitmap.width
        }

        override fun draw(canvas: Canvas?) {
            canvas?.drawBitmap(bitmap, bounds.left.toFloat(), bounds.top.toFloat(), paint)
        }

        override fun setAlpha(alpha: Int) {
        }

        override fun getOpacity(): Int {
            return PixelFormat.UNKNOWN
        }

        override fun setColorFilter(cf: ColorFilter?) {
            paint.colorFilter = cf
        }

    }
}