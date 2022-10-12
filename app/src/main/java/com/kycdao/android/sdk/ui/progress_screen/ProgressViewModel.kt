package com.kycdao.android.sdk.ui.progress_screen

import android.app.Activity
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kycdao.android.sdk.kycSession.KycManager
import com.kycdao.android.sdk.model.KycSession
import com.kycdao.android.sdk.model.PersonalDataResult
import com.kycdao.android.sdk.network.NetworkDatasource
import com.kycdao.android.sdk.network.NetworkDatasourceImpl
import com.kycdao.android.sdk.network.api.APIService
import com.kycdao.android.sdk.ui.progress_screen.model.Step
import com.kycdao.android.sdk.wallet.WalletConnectManager
import com.kycdao.android.sdk.wallet.WalletSession
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent
import timber.log.Timber

class ProgressViewModel(
    private val activity: ComponentActivity
) : ViewModel() {
    val kycManager = KycManager()
    var myWalletSession: WalletSession? = null
    var myKycSession = MutableStateFlow<KycSession?>(null)
    var selectedImage: String? = null

    fun createKycSession() {
        viewModelScope.launch {
            myWalletSession?.let { session ->
                val choosenWallet =
                    session.getAvailableWallets()?.first() ?: throw Exception("No wallet")
                myKycSession.update { kycManager.createSession(choosenWallet, session) }
            }
        }
    }

    fun testSuspend() {
        viewModelScope.launch {
            try {
                kycManager.testSuspend()
            } catch (e: CancellationException) {
                Timber.d("Job was cancelled")
                e.printStackTrace()
            }
        }
    }

    fun connectToWallet() {
        WalletConnectManager.connectWallet { createdSession ->
            myWalletSession = createdSession
        }
    }

    fun login() {
        viewModelScope.launch {
            myWalletSession?.let { session ->
                val choosenWallet =
                    session.getAvailableWallets()?.first() ?: throw Exception("No wallet")
                val signature = session.personalSign(
                    walletAddress = choosenWallet,
                    message = myKycSession.value?.loginProof()
                        ?: throw Exception("NoKycSession found")
                )
                kycManager.login(signature)
            }
        }
    }

    fun startPersona() {
        viewModelScope.launch {
            kycManager.startPersonaIdentification(activity)
        }
    }

    fun selectNft() {
        viewModelScope.launch {
            selectedImage = kycManager.selectAndPrepareForNFTMinting(activity)
            Timber.d("Selected image is: $selectedImage")
        }
    }

    fun mintNft() {
        viewModelScope.launch {
            kycManager.mint {
                myWalletSession?.let { session ->
                    val choosenWallet =
                        session.getAvailableWallets()?.first() ?: throw Exception("No wallet")
                    session.sendMintingTransaction(choosenWallet, it)
                }
            }
        }
    }

    fun savePersonalInfo() {
        val mockPersonalInfo = PersonalDataResult(
            email = "adamszucs2000@gmail.com",
            residency = "HUN",
            isLegalEntity = false
        )
        viewModelScope.launch {
            kycManager.savePersonalInfo(mockPersonalInfo)
        }
    }
}