package com.sergio.mozpertest.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.sergio.mozpertest.databinding.LoadingViewBinding

class LoadingView private constructor(
    private var parentView: ViewGroup,
    private var context: Context
) {

    private var binding: LoadingViewBinding? = null
    private var isShowing: Boolean = false

    /**
     * Shows Loading View
     */
    fun show() {
        isShowing = true
        binding?.run {
            root.visibility = VISIBLE
            // Lottie possibility
            // loadingAnimation.playAnimation()
        } ?: run {
            binding = LoadingViewBinding.inflate(
                LayoutInflater.from(context),
                parentView,
                false
            ).apply {
                parentView.addView(root)
                binding.apply {
                    root.visibility = VISIBLE
                    // Lottie possibility
                    // loadingAnimation.playAnimation()
                }
            }
        }
    }

    /**
     * Dismiss Loading View
     */
    fun dismiss() {
        isShowing = false
        binding?.run {
            root.visibility = GONE
            // Lottie possibility
            // loadingAnimation.pauseAnimation()
        }
    }

    companion object {
        fun create(viewGroup: ViewGroup, context: Context) =
            LoadingView(viewGroup, context)
    }
}
