package pl.olszak.michal.todo.viewmodel.bindingadapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.databinding.BindingAdapter
import android.support.design.widget.BottomNavigationView
import android.view.View
import pl.olszak.michal.todo.R

/**
 * @author molszak
 *         created on 08.02.2018.
 */
//todo: bind animatedVisibility
@BindingAdapter(value = ["animatedVisibility"], requireAll = false)
fun bindVisibility(view: BottomNavigationView, animatedVisibility: Boolean?) {
    animatedVisibility?.let {
        val endVisibility: Int? = view.getTag(R.id.finalVisibility) as Int?
        val oldVisibility = endVisibility ?: view.visibility

        val visibility = if (it) View.VISIBLE else View.INVISIBLE
        if (oldVisibility == visibility) {
            return
        }

        val isVisible = oldVisibility == View.VISIBLE
        view.visibility = View.VISIBLE
        var startAlpha = if (isVisible) 1f else 0f
        if (endVisibility != null) {
            startAlpha = view.alpha
        }
        val endAlpha = if (it) 1f else 0f

        val animator = ObjectAnimator.ofFloat(view, View.ALPHA, startAlpha, endAlpha)
        animator.setAutoCancel(true)

        animator.addListener(object : AnimatorListenerAdapter() {
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
                    view.alpha = 1f
                    view.visibility = visibility
                }
            }
        })
        animator.start()
    }
}