package spoti.hu.kyctestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kycdao.android.sdk.model.VerificationType
import com.kycdao.android.sdk.verificationSession.VerificationManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import spoti.hu.kyctestapp.manager.SDKManager

class WalletConnectedViewModel(
	private val sdkManager: SDKManager
) : ViewModel(){
	val walletHasToken = MutableStateFlow<Boolean?>(null)
	val verifiedNetworksMap = MutableStateFlow<Map<String,Boolean>?>(null)
	fun checkWalletToken(){
		viewModelScope.launch {
			sdkManager.getWalletConnectSession().let { walletSession ->
				val walletAddress =
					walletSession.getAvailableWallets()?.first() ?: throw Exception("No wallet found.")
				walletHasToken.update {
					VerificationManager.hasValidToken(
						verificationType = VerificationType.KYC,
						walletAddress = walletAddress,
						walletSession = walletSession
					)
				}
			}
		}
	}
	fun checkVerifications(){
		viewModelScope.launch {
			sdkManager.getWalletConnectSession().let { walletSession ->
				val walletAddress =
					walletSession.getAvailableWallets()?.first()
						?: throw Exception("No wallet found.")
				verifiedNetworksMap.update { VerificationManager.checkVerifiedNetworks(VerificationType.KYC,walletAddress) }
			}
		}
	}
	val connectedWalletId = sdkManager.connectedWalletID.distinctUntilChanged()
}