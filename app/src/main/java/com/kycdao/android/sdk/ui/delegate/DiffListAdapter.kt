package com.kycdao.android.sdk.ui.delegate

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class DiffListAdapter(
    vararg adapters: AdapterDelegate<List<ListItem>>
) : AsyncListDifferDelegationAdapter<ListItem>(diffCallback) {

    val listItems: List<ListItem> get() = items
    val itemsCount: Int get() = itemCount

    init {
        addDelegates(*adapters)
    }

    fun addDelegates(vararg delegates: AdapterDelegate<List<ListItem>>) {
        delegates.forEach {
            delegatesManager.addDelegate(it)
        }
    }

    fun setFallbackDelegate(delegate: AdapterDelegate<List<ListItem>>) {
        delegatesManager.fallbackDelegate = delegate
    }

    fun notifyItemUpdate(pos: Int) {
        notifyItemChanged(pos)
    }

    fun updateData(list: List<ListItem>) {
        items = list
    }

    companion object {
        private val diffCallback: DiffUtil.ItemCallback<ListItem> =
            object : DiffUtil.ItemCallback<ListItem>() {
                override fun areItemsTheSame(
                    oldItem: ListItem,
                    newItem: ListItem
                ): Boolean {
                    return oldItem.getAdapterItemId() == newItem.getAdapterItemId()
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: ListItem,
                    newItem: ListItem
                ): Boolean {
                    return oldItem.getAdapterItemHash() == newItem.getAdapterItemHash()
                }
            }
    }
}