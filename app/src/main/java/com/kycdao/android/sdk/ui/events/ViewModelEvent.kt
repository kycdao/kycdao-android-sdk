package com.kycdao.android.sdk.ui.events

interface ViewModelEvent

data class MakeToastEvent(val message: String) : ViewModelEvent
