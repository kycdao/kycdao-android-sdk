package spoti.hu.kyctestapp.navigation

import com.kycdao.android.sdk.model.VerificationStatus
import com.kycdao.android.sdk.model.VerificationType
import com.kycdao.android.sdk.verificationSession.VerificationSession
import spoti.hu.kyctestapp.R
import spoti.hu.kyctestapp.manager.SDKManager

class NavigationManagerImpl(private val sdkManager: SDKManager) : NavigationManager {

	override fun getNextDestination() : Int {
		val verificationSession = sdkManager.getVerificationSession()
		return when{
			!verificationSession.loggedIn -> R.id.action_global_createSignatureFragment
			!verificationSession.requiredInformationProvided -> R.id.action_global_informationRequestFragment
			!verificationSession.emailConfirmed -> R.id.action_global_confirmEmailFragment
			verificationSession.verificationStatus != VerificationStatus.VERIFIED -> R.id.action_global_personaFragment
			!verificationSession.hasMembership -> R.id.action_global_selectMembershipFragment
			else -> throw Exception("Destination can not be determined")
		}
	}
}