package com.kycdao.android.sdk.ui

import androidx.fragment.app.Fragment
import java.io.Serializable

interface FragmentFactory : Serializable {
    fun getFragment() : Fragment
}