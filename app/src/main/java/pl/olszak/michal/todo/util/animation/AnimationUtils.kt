package pl.olszak.michal.todo.util.animation

import android.animation.Animator
import android.content.Context
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.view.View
import android.view.ViewAnimationUtils
import pl.olszak.michal.todo.util.animation.model.RevealAnimationSetting

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
                    }
                }

            })
        }
    }

}