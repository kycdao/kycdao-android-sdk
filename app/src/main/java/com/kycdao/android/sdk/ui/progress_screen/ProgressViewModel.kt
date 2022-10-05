package com.kycdao.android.sdk.ui.progress_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kycdao.android.sdk.ui.progress_screen.model.Step

class ProgressViewModel: ViewModel() {
    val listItems: LiveData<List<Step>> = ProgressManager.output.asLiveData()
}