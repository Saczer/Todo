package pl.olszak.michal.todo.view.animation

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.view.View
import android.view.ViewAnimationUtils
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.util.extension.getCurrentAccentColor
import pl.olszak.michal.todo.view.animation.model.RevealAnimationSetting

/**
 * @author molszak
 *         created on 06.02.2018.
 */
class AnimationUtils {

    companion object Factory {
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

    }

}