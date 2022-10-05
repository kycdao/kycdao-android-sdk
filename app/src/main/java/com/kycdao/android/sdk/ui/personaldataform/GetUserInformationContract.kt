package com.kycdao.android.sdk.ui.personaldataform

import androidx.fragment.app.Fragment
import com.kycdao.android.sdk.model.PersonalDataResult
import com.kycdao.android.sdk.ui.FragmentFactory
import com.kycdao.android.sdk.ui.KycActivityResultContract

class GetUserInformationContract : KycActivityResultContract<Any?, PersonalDataResult>() {

    override fun fragmentFactory(input: Any?): FragmentFactory {
        return object : FragmentFactory {
            override fun getFragment(): Fragment {
                return PersonalFormFragment.newInstance()
            }
        }
    }

}