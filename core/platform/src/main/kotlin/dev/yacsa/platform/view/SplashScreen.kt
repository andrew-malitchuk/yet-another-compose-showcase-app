package dev.yacsa.platform.view

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreenViewProvider
import kotlin.math.hypot


fun animate(context: Context, splashScreenView: SplashScreenViewProvider?) {
    val ANIMATION_DURATION = 500L
    val SCALE_START = 1f
    val SCALE_END = 50f
    val ALPHA_START = 1f
    val ALPHA_END = 0f

    //region Animate alpha
    val alphaAnimation = ObjectAnimator.ofFloat(
        splashScreenView?.view, View.ALPHA, ALPHA_START, ALPHA_END,
    ).apply {
        interpolator = DecelerateInterpolator()
        duration = ANIMATION_DURATION
    }
    //endregion Animate alpha
    //region Animate background
    val colorFrom = Color.parseColor("#6667AB")
    val colorTo = Color.parseColor("#000000")
    val colorAnimation = ValueAnimator.ofObject(
        ArgbEvaluator(), colorFrom, colorTo
    ).apply {
        addUpdateListener {
            splashScreenView?.view?.setBackgroundColor((it.animatedValue as Int))
        }
        interpolator = DecelerateInterpolator()
        duration = ANIMATION_DURATION
    }
    //endregion Animate background
    //region Scale
    val scaleAnimation: ValueAnimator =
        ValueAnimator.ofFloat(SCALE_START, SCALE_END).apply {
            addUpdateListener { av ->
                splashScreenView?.view?.let {
                    it.scaleX = (av.animatedValue as Float)
                    it.scaleY = (av.animatedValue as Float)
                }
            }
            doOnEnd { splashScreenView?.remove() }
            interpolator = DecelerateInterpolator()
            duration = ANIMATION_DURATION
        }
    //endregion Scale
    //region Reveal
    val cx = (splashScreenView?.view?.width ?: 1) / 2
    val cy = (splashScreenView?.view?.height ?: 1) / 2
    val initialRadius = hypot(cx.toDouble(), cy.toDouble()).toFloat()
    val revealAnimation =
        ViewAnimationUtils.createCircularReveal(splashScreenView?.view, cx, cy, initialRadius, 0f).apply {
//            doOnEnd { splashScreenView?.remove() }
//            interpolator = DecelerateInterpolator()
//            duration = ANIMATION_DURATION
        }
    //endregion Reveal
    AnimatorSet().apply {
        playTogether(
            alphaAnimation, scaleAnimation, /*colorAnimation*/
        )
    }.start()
}