package pl.olszak.michal.todo.view.preferences

import android.content.Context
import android.content.res.TypedArray
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import pl.olszak.michal.todo.R

/**
 * @author molszak
 *         created on 02.02.2018.
 */
class PreferenceTitle @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val title: TextView
    @StringRes
    private var titleRes: Int = 0

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.preference_title, this)

        title = findViewById(R.id.title)

        attrs?.let {
            val attrArray: TypedArray = context.obtainStyledAttributes(it, R.styleable.PreferenceTitle)
            titleRes = attrArray.getResourceId(R.styleable.PreferenceTitle_pt_title, 0)
            attrArray.recycle()
        }

    }

    override fun onFinishInflate() {
        title.setText(titleRes)
        super.onFinishInflate()
    }


}