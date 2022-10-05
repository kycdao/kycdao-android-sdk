package com.kycdao.android.sdk.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.kycdao.android.sdk.db.LocalDataSource
import com.kycdao.android.sdk.ui.progress_screen.ProgressFragment
import com.kycdao.android.sdk.usecase.TestUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kycdao.android.sdk.R
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.core.scope.Scope

class ProgressActivity : AppCompatActivity(), AndroidScopeComponent {

    private val localDataSource: LocalDataSource by inject()
    override val scope : Scope by activityScope()
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val testUseCase: TestUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.frame_layout)

        if (savedInstanceState == null) {
            openFragment(ProgressFragment())
            ioScope.launch {
                testUseCase()
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitNow()
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.close()
    }

}