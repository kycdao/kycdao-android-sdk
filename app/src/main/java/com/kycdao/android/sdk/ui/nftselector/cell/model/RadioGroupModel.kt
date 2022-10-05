package com.kycdao.android.sdk.ui.nftselector.cell.model

import com.kycdao.android.sdk.ui.delegate.ListItem
import com.kycdao.android.sdk.ui.events.SingleLiveEvent
import com.kycdao.android.sdk.ui.events.ViewModelEvent

class RadioGroupModel(
    val choices: List<RadioButtonModel>,
    val onItemSelected: (Int, event: SingleLiveEvent<ViewModelEvent>) -> Unit
): ListItem {
    override fun getAdapterItemHash(): Int {
        var content = onItemSelected.toString()
        choices.forEach {
            content += it.getContentString()
        }
        return content.hashCode()
    }
}