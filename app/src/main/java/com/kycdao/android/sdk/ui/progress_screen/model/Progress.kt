package com.kycdao.android.sdk.ui.progress_screen.model

import androidx.annotation.DrawableRes
import kycdao.android.sdk.R

enum class Progress (@DrawableRes val icon: Int?) {
    TODO(null), IN_PROGRESS(null), ERROR(R.drawable.ic_x), DONE(R.drawable.ic_tick)
}