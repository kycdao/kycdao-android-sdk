package com.kycdao.android.sdk.ui

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout

class ToggleableAppBarLayoutBehavior(
    context: Context,
    attrs: AttributeSet? = null,
): AppBarLayout.Behavior(context, attrs) {

    private var allowScroll = false

    override fun onStartNestedScroll(
        parent: CoordinatorLayout,
        child: AppBarLayout,
        directTargetChild: View,
        target: View,
        nestedScrollAxes: Int,
        type: Int
    ): Boolean {
        return if (isScrollingAllowed()) {
            super.onStartNestedScroll(
                parent,
                child,
                directTargetChild,
                target,
                nestedScrollAxes,
                type
            )
        } else {
            false
        }
    }

    override fun onTouchEvent(
        parent: CoordinatorLayout,
        child: AppBarLayout,
        ev: MotionEvent
    ): Boolean {
        return if (isScrollingAllowed()) {
            super.onTouchEvent(parent, child, ev)
        } else {
            false
        }
    }

    fun enableScroll(enable: Boolean) {
        allowScroll = enable
    }

    fun isScrollingAllowed() = allowScroll

}