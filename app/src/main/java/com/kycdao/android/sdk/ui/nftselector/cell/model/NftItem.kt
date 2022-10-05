package com.kycdao.android.sdk.ui.nftselector.cell.model

data class NftItem(
    override val id: String,
    override val imageUrl: String,
    override val idx: Int,
    override val isSelected: Boolean
) : RadioButtonModel(
    id = id,
    imageUrl = imageUrl,
    idx = idx,
    isSelected = isSelected
)