package com.kycdao.android.sdk.ui.progress_screen.model

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kycdao.android.sdk.R

class Action(
    val label: String,
    val enabled: Boolean,
    val function: suspend () -> Unit,
) {
    fun getButton(context: Context): ActionButton {
        return ActionButton(this, context)
    }

    fun getLayoutParams(context: Context): ConstraintLayout.LayoutParams {
        return ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(
                0,
                0,
                context.resources.getDimensionPixelSize(R.dimen.spacing_normal) / 2,
                0
            )
        }
    }
}

@SuppressLint("ViewConstructor")
class ActionButton(
    action: Action,
    context: Context,
    attributeSet: AttributeSet? = null,
    theme: Int = android.R.style.Widget_Material_Button_Small,
) : MaterialButton(context, attributeSet, theme) {

    private val ioScope = CoroutineScope(Dispatchers.IO)

    init {
        text = action.label
        isEnabled = action.enabled
        alpha = if (action.enabled) 1f else 0.4f
        insetTop = 12
        insetBottom = 12
        setPadding(30, 30, 30, 30)
        textSize = 14f
        cornerRadius = 18
        backgroundTintList = ColorStateList.valueOf(context.getColor(R.color.main_color_secondary))

        setOnClickListener { ioScope.launch { action.function() } }
    }
}