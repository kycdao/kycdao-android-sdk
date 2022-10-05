package com.kycdao.android.sdk.ui.nftselector

import androidx.fragment.app.Fragment
import com.kycdao.android.sdk.model.AvailableImage
import com.kycdao.android.sdk.ui.FragmentFactory
import com.kycdao.android.sdk.ui.KycActivityResultContract

class NftSelectorContract : KycActivityResultContract<List<AvailableImage>, String>() {

    override fun fragmentFactory(input: List<AvailableImage>): FragmentFactory {
        return object : FragmentFactory {
            override fun getFragment(): Fragment {
                return NftSelectorFragment.newInstance(input)
            }
        }
    }

}