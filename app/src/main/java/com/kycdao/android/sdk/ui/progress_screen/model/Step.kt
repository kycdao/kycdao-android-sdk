package com.kycdao.android.sdk.ui.progress_screen.model

import com.kycdao.android.sdk.ui.delegate.ListItem

data class Step(
    val title: String,
    var desc: String = "",
    var actions: List<Action> = emptyList(),
    var progress: Progress = Progress.TODO,
    var show: Boolean = false
): ListItem