package com.kycdao.android.sdk.ui.nftselector.cell

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.children
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.kycdao.android.sdk.ui.delegate.ListItem
import com.kycdao.android.sdk.ui.events.SingleLiveEvent
import com.kycdao.android.sdk.ui.events.ViewModelEvent
import com.kycdao.android.sdk.ui.nftselector.cell.model.RadioButtonModel
import com.kycdao.android.sdk.ui.nftselector.cell.model.RadioButtonView
import com.kycdao.android.sdk.ui.nftselector.cell.model.RadioGroupModel
import kycdao.android.sdk.databinding.CellRadiogroupBinding

fun cellRadioGroup(event: SingleLiveEvent<ViewModelEvent>) = adapterDelegateViewBinding<RadioGroupModel, ListItem, CellRadiogroupBinding>(
    viewBinding = { layoutInflater, root ->
        CellRadiogroupBinding.inflate(layoutInflater, root, false)
    },
    block = {
        bind {
            binding.apply {
                if (item.choices.isNotEmpty()) {
                    binding.putInAsHorizontalChain(item.choices, context) { id ->
                        item.onItemSelected(id, event)
                    }
                }
            }
        }
    }
)

private fun CellRadiogroupBinding.putInAsHorizontalChain(
    choices: List<RadioButtonModel>,
    context: Context,
    onItemSelected: (Int)-> Unit,
) {
    //remove old views
    nftChoices.removeAllViews()

    //put views in
    choices.forEach { model ->
        val viewFromModel = RadioButtonView(context).apply {
            //make
            this.id = View.generateViewId()

            //setup
            this.setContent(model){
                //notify
                onItemSelected(model.idx)
                //deselect all
                nftChoices.children.forEach {
                    (it as? RadioButtonView)?.deselect()
                }
                //select this
                this.select()
            }
        }
        nftChoices.addView(viewFromModel)
    }

    //make constraint set
    val constraintSet = ConstraintSet().apply { clone(nftChoices) }
    var previousView : View? = null
    nftChoices.children.forEachIndexed { index, view ->
        if (index == 0) {
            constraintSet.connect(
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT
            )
        } else {
            constraintSet.connect(
                previousView!!.id,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT
            )
            if (index == nftChoices.childCount) {
                constraintSet.connect(
                    previousView!!.id,
                    ConstraintSet.LEFT,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.RIGHT
                )
            }
        }
        previousView = view
    }
    val viewIds = mutableListOf<Int>()
    nftChoices.children.forEach {
        viewIds.add(it.id)
    }
    constraintSet.createHorizontalChain(
        ConstraintSet.PARENT_ID,
        ConstraintSet.LEFT,
        ConstraintSet.PARENT_ID,
        ConstraintSet.RIGHT,
        viewIds.toIntArray(),
        null,
        ConstraintSet.CHAIN_SPREAD
    )
    nftChoices.children.forEach { view ->
        constraintSet.constrainWidth(view.id, ConstraintSet.MATCH_CONSTRAINT_SPREAD)
    }
    constraintSet.applyTo(nftChoices)
}