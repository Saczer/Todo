package pl.olszak.michal.todo.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Parcelable
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.Checkable
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.cache.model.ThemePalette
import pl.olszak.michal.todo.util.extension.calculateBounds
import pl.olszak.michal.todo.util.tools.TodoUtils

/**
 * @author molszak
 *         created on 02.02.2018.
 */
class ThemeView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), Checkable {

    companion object {
        private const val FACTOR = .8f
        private const val STROKE = 6f
        private const val STATE = "SavedInstanceState"
        private const val CHECKED = "Checked"
    }

    private var checked: Boolean = false

    private val checkedDrawable: Drawable
    private val drawableGravity = Gravity.CENTER

    private val borderCircleRect = RectF()
    private val borderColor = Paint(Paint.ANTI_ALIAS_FLAG)
    private var borderCircleRadius = 0f

    @ColorInt
    private var circleColor: Int = Color.WHITE
    private val circlePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val circleRect = RectF()
    private var circleRadius = 0f

    internal var listener: OnStateChangeListener? = null
    var themePalette: ThemePalette? = null
        set(value) {
            if (field != value && value != null) {
                field = value
                circleColor = TodoUtils.getColor(context, value)
                invalidateColor()
                invalidate()
            }
        }

    init {
        initializeAttributes(attrs)
        checkedDrawable = CheckedDrawable(context)
        outlineProvider = OutlineProvider()
        setup()
        isClickable = true
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
            it.drawCircle(circleRect.centerX(), circleRect.centerY(), circleRadius, circlePaint)
            if (checked) {
                checkedDrawable.draw(it)
//                it.drawCircle(borderCircleRect.centerX(), borderCircleRect.centerY(), borderCircleRadius, borderColor)
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setup()
    }

    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        super.setPadding(left, top, right, bottom)
        setup()
    }

    override fun setPaddingRelative(start: Int, top: Int, end: Int, bottom: Int) {
        super.setPaddingRelative(start, top, end, bottom)
        setup()
    }

    override fun onSaveInstanceState(): Parcelable {
        val bundle = Bundle()
        bundle.putParcelable(STATE, super.onSaveInstanceState())
        bundle.putBoolean(CHECKED, checked)
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        state?.let {
            if (it is Bundle) {
                checked = it.getBoolean(CHECKED)
                super.onRestoreInstanceState(it.getBundle(STATE))
            }
        }
    }

    override fun performClick(): Boolean {
        toggle()
        return super.performClick()
    }

    private fun setup() {
        if (width == 0 && height == 0) {
            return
        }

        borderCircleRect.set(calculateBounds())
        borderCircleRadius = Math.min((borderCircleRect.height() - STROKE) / 2f,
                (borderCircleRect.width() - STROKE) / 2f)

        circleRect.set(borderCircleRect)
        circleRadius = Math.min(circleRect.height() / 2f, circleRect.width() / 2f)
        layoutCheckedDrawable()
        invalidate()
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
    }

    private fun invalidateColor() {
        circlePaint.apply {
            color = circleColor
        }
        borderColor.apply {
            color = TodoUtils.darkenColor(circleColor, FACTOR)
            strokeWidth = STROKE
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
        }
    }

    override fun setChecked(checked: Boolean) {
        if (this.checked != checked) {
            this.checked = checked
            invalidate()
        }
    }

    override fun toggle() {
        if (!checked) {
            checked = !checked
            listener?.onStateChange(this, checked)
            invalidate()
        }
    }

    override fun isChecked(): Boolean {
        return checked
    }

    internal interface OnStateChangeListener {

        fun onStateChange(view: ThemeView, checked: Boolean)

    }

    private inner class OutlineProvider : ViewOutlineProvider() {
        override fun getOutline(view: View?, outline: Outline?) {
            val bounds = Rect()
            borderCircleRect.roundOut(bounds)
            outline?.setRoundRect(bounds, bounds.width() / 2f)
        }

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