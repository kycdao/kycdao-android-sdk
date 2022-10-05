package com.kycdao.android.sdk.ui.nftselector.cell.model

import com.kycdao.android.sdk.ui.delegate.ListItem
import com.kycdao.android.sdk.ui.events.ViewModelEvent

sealed class PagerPageModel (
    val items: List<ListItem>,
)

class NftChoicePage(nftItems: List<RadioButtonModel>, selected: Int?): PagerPageModel(
    items = listOf(
        RadioGroupModel(
            choices = nftItems.map { item ->
                NftItem(
                    id = item.id,
                    idx = nftItems.indexOf(item),
                    isSelected = selected == nftItems.indexOf(item),
                    imageUrl = item.imageUrl
                )
            },
            onItemSelected = { id, events ->
                events.postValue(NftItemSelected(id, nftItems[id] as NftItem))
            },
        )
    ),
)

data class NftItemSelected(val id: Int, val item: NftItem): ViewModelEvent