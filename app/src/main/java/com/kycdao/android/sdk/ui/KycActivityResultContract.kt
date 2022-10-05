package com.kycdao.android.sdk.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import androidx.annotation.CallSuper
import java.io.Serializable

abstract class KycActivityResultContract<Input, Result : Serializable> : ActivityResultContract<Input, Result?>() {

    companion object {
        const val FRAGMENT_FACTORY = "fragment_factory"
        const val RESULT_KEY = "result"
    }

    @CallSuper
    override fun createIntent(context: Context, input: Input): Intent {
        return Intent(context, activityClass())
            .putExtra(FRAGMENT_FACTORY, fragmentFactory(input))
    }

    @Suppress("UNCHECKED_CAST")
    override fun parseResult(resultCode: Int, intent: Intent?): Result? {
        return intent.takeIf { resultCode == Activity.RESULT_OK }
            ?.extras?.getSerializable(RESULT_KEY) as? Result
    }

    open fun activityClass(): Class<SdkActivity> {
        return SdkActivity::class.java
    }

    abstract fun fragmentFactory(input: Input): FragmentFactory

}