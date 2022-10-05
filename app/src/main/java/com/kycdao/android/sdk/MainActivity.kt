package com.kycdao.android.sdk
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kycdao.android.sdk.db.LocalDataSource
import com.kycdao.android.sdk.ui.ProgressActivity
import com.kycdao.android.sdk.usecase.TestUseCase
import kycdao.android.sdk.databinding.ScreenMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.core.scope.Scope

class MainActivity : AppCompatActivity(), AndroidScopeComponent {

    override val scope : Scope by activityScope()
    private val testUseCase: TestUseCase by inject()
    private val localDataSource: LocalDataSource by inject()

    private lateinit var binding: ScreenMainBinding
    private val ioScope = CoroutineScope(Dispatchers.IO)

    companion object{
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ScreenMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() = with(binding) {
        super.onStart()
        start.setOnClickListener {
            startActivity(Intent(this@MainActivity, ProgressActivity::class.java))
//            ioScope.launch {
//                testUseCase()
//            }
        }
//        screenMainDisconnectButton.setOnClickListener {
//            localDataSource.getWCSession().session.kill()
//        }
    }

}
