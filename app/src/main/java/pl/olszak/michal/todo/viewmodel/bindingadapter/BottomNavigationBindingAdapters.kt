package pl.olszak.michal.todo.viewmodel.bindingadapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.databinding.BindingAdapter
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.view.View
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.util.extension.shouldAnimateVisibilityChange

/**
 * @author molszak
 *         created on 08.02.2018.
 */
//todo: move it to animation utils?
@BindingAdapter(value = ["animatedVisibility"], requireAll = false)
fun bindVisibility(view: BottomNavigationView, animatedVisibility: Boolean?) {
    animatedVisibility?.let {
        if (shouldAnimateVisibilityChange(view)) {

            val endVisibility: Int? = view.getTag(R.id.finalVisibility) as Int?
            val oldVisibility = endVisibility ?: view.visibility

            val visibility = if (it) View.VISIBLE else View.INVISIBLE
            if (oldVisibility == visibility) {
                return
            }

            val isVisible = oldVisibility == View.VISIBLE
            view.visibility = View.VISIBLE
            var startTranslationY = if (isVisible) 0f else view.height.toFloat()
            if (endVisibility != null) {
                startTranslationY = view.translationY
            }
            val endTranslationY = if (it) 0f else view.height.toFloat()

            val animator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, startTranslationY, endTranslationY)
            animator.apply {
                interpolator = FastOutSlowInInterpolator()
                setAutoCancel(true)

                addListener(object : AnimatorListenerAdapter() {
                    var isCancelled: Boolean = false

                    override fun onAnimationStart(animation: Animator?) {
                        view.setTag(R.id.finalVisibility, visibility)
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                        isCancelled = true
                    }

                    override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                        view.setTag(R.id.finalVisibility, null)
                        if (!isCancelled) {
                            view.translationY = 0f
                            view.visibility = visibility
                        }
                    }
                })
            }
            animator.start()
        } else {
            view.visibility = if (it) View.VISIBLE else View.INVISIBLE
        }
    }
}