package com.kycdao.android.sdk.ui.delegate

interface ListItem {
    fun getAdapterItemId(): String = this::class.java.name
    fun getAdapterItemHash(): Int = hashCode()
}