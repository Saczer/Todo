package pl.olszak.michal.todo.view.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.view.View
import android.view.ViewAnimationUtils
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.util.extension.getCurrentAccentColor
import pl.olszak.michal.todo.util.extension.shouldAnimateVisibilityChange
import pl.olszak.michal.todo.view.animation.model.RevealAnimationSetting

/**
 * @author molszak
 *         created on 06.02.2018.
 */
class TodoAnimationUtils {

    companion object Factory {

        //todo : move final color to navigator
        fun registerCircularRevealAnimation(context: Context,
                                            view: View,
                                            revealSettings: RevealAnimationSetting) {
            view.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
                override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
                    v?.let {
                        it.removeOnLayoutChangeListener(this)
                        val (cx, cy, width, height) = revealSettings

                        val duration = context.resources.getInteger(android.R.integer.config_mediumAnimTime)
                        val radius: Float = Math.sqrt((width * width + height * height).toDouble()).toFloat()

                        val anim: Animator = ViewAnimationUtils.createCircularReveal(it, cx, cy, 0f, radius)
                        anim.apply {
                            setDuration(duration.toLong())
                            interpolator = FastOutSlowInInterpolator()
                        }

                        anim.start()
                        val startColor: Int = getCurrentAccentColor(it.context)
                        val endColor: Int = ContextCompat.getColor(it.context, R.color.blackOpacity54)
                        startColorAnimation(it, startColor, endColor, duration.toLong())
                    }
                }
            })
        }

        fun startColorAnimation(view: View, @ColorInt startColor: Int, @ColorInt endColor: Int, duration: Long) {
            val animator = ValueAnimator()
            animator.apply {
                setIntValues(startColor, endColor)
                setEvaluator(ArgbEvaluator())
                addUpdateListener {
                    view.setBackgroundColor(it.animatedValue as Int)
                }
                setDuration(duration)
            }

            animator.start()
        }

        fun animateVisibility(view: View, visibilityChange: Boolean) {
            view.animate().cancel()

            if (shouldAnimateVisibilityChange(view)) {

                val endVisibility: Int? = view.getTag(R.id.finalVisibility) as Int?
                val oldVisibility = endVisibility ?: view.visibility

                val visibility = if (visibilityChange) View.VISIBLE else View.INVISIBLE
                if (oldVisibility == visibility) {
                    return
                }

                val endTranslationY = if (visibilityChange) 0f else view.height.toFloat()

                view.animate()
                        .translationY(endTranslationY)
                        .setDuration(200L)
                        .setInterpolator(LinearOutSlowInInterpolator())
                        .setListener(object : AnimatorListenerAdapter() {
                            private var cancelled: Boolean = false

                            override fun onAnimationStart(animation: Animator?) {
                                view.visibility = visibility
                            }

                            override fun onAnimationCancel(animation: Animator?) {
                                cancelled = true
                            }

                            override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                                if (!cancelled && !visibilityChange) {
                                    view.visibility = View.GONE
                                }
                            }
                        })
            } else {
                view.translationY = 0f
                view.visibility = if (visibilityChange) View.VISIBLE else View.INVISIBLE
            }
        }

    }

}