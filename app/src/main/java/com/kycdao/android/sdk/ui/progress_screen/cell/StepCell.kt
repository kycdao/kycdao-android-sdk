package com.kycdao.android.sdk.ui.progress_screen.cell

import android.view.LayoutInflater
import android.widget.Button
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.kycdao.android.sdk.ui.delegate.ListItem
import com.kycdao.android.sdk.ui.progress_screen.model.Progress
import com.kycdao.android.sdk.ui.progress_screen.model.Step
import com.kycdao.android.sdk.ui.setDrawable
import com.kycdao.android.sdk.ui.setupAndShowOnCondition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kycdao.android.sdk.R
import kycdao.android.sdk.databinding.CellStepItemBinding

private val ioScope = CoroutineScope(Dispatchers.IO)

fun cellStepItem() = adapterDelegateViewBinding<Step, ListItem, CellStepItemBinding>(
    viewBinding = { layoutInflater, parent ->
        CellStepItemBinding.inflate(layoutInflater, parent, false)
    },
    block = {
        val inflater = LayoutInflater.from(context)

        bind {
            binding.title.text = item.title
            binding.progressIcon.setDrawable(item.progress.icon)

            binding.desc.setupAndShowOnCondition(item.desc.isNotBlank()) { view ->
                view.text = item.desc
            }

            binding.actionButtonHolder.apply {
                removeAllViews()
                item.actions.forEach { action ->
                    val button = inflater.inflate(R.layout.cell_step_item_action_button, this, false) as Button
                    button.text = action.label
                    button.isEnabled = action.enabled
                    button.setOnClickListener { ioScope.launch { action.function() } }
                    addView(button)
                }
            }

            when (item.progress) {
                Progress.TODO, Progress.ERROR, Progress.DONE -> {
                    binding.spinner.hide()
                }
                Progress.IN_PROGRESS -> {
                    binding.spinner.show()
                    binding.spinner.animate()
                }
            }
        }
    }
)