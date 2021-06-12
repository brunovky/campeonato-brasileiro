package com.brunooliveira.campeonatobrasileiro.view.animator

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.widget.ProgressBar

class LoadingAnimator {

    companion object {

        fun animateLoading(loading: Boolean, view: View, progress: ProgressBar) {
            progress.visibility = if (loading) View.VISIBLE else View.GONE
            val animatorSet = AnimatorSet()
            val scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX",
                if (loading) 1f else 0.95f, if (loading) 0.95f else 1f)
            val scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY",
                if (loading) 1f else 0.95f, if (loading) 0.95f else 1f)
            val alphaAnimator = ObjectAnimator.ofFloat(view, "alpha",
                if (loading) 1f else 0.3f, if (loading) 0.3f else 1f)
            scaleXAnimator.duration = 500
            scaleYAnimator.duration = 500
            alphaAnimator.duration = 500
            animatorSet.playTogether(scaleXAnimator, scaleYAnimator, alphaAnimator)
            scaleXAnimator.start()
            scaleYAnimator.start()
            alphaAnimator.start()
        }

    }

}