package com.kycdao.android.sdk.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kycdao.android.sdk.R

class SdkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.frame_layout)

        if (savedInstanceState == null) {
            takeFragment()?.let { openFragment(it) }
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitNow()
    }

    private fun takeFragment(): Fragment? {
        val fragmentFactory =
            intent.extras?.getSerializable(KycActivityResultContract.FRAGMENT_FACTORY) as? FragmentFactory
        return fragmentFactory?.getFragment()
    }
}