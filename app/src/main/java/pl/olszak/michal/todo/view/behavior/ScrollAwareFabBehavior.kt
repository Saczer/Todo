package pl.olszak.michal.todo.view.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.util.AttributeSet
import android.view.View

/**
 * @author molszak
 *         created on 31.01.2018.
 */
class ScrollAwareFabBehavior(
        context: Context?,
        attrs: AttributeSet?
) : FloatingActionButton.Behavior(context, attrs) {

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout,
                                child: FloatingActionButton,
                                target: View, dxConsumed: Int,
                                dyConsumed: Int,
                                dxUnconsumed: Int,
                                dyUnconsumed: Int,
                                type: Int) {

        super.onNestedScroll(coordinatorLayout,
                child,
                target,
                dxConsumed,
                dyConsumed,
                dxUnconsumed,
                dyUnconsumed,
                type)

        if (dyConsumed > 0 && child.visibility == View.VISIBLE) {
            child.hide()
        } else if (dyConsumed < 0 && child.visibility != View.VISIBLE) {
            child.show()
        }
    }
}