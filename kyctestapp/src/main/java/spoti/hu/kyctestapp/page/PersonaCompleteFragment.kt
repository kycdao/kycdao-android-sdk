package spoti.hu.kyctestapp.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.kycdao.android.sdk.model.IdentityFlowResult
import com.kycdao.android.sdk.model.VerificationStatus
import kotlinx.coroutines.launch
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentPersonaCompleteBinding

class PersonaCompleteFragment : BaseFragment<FragmentPersonaCompleteBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPersonaCompleteBinding {
        return FragmentPersonaCompleteBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupVerification()
    }


    private fun setupVerification() {
        lifecycleScope.launch {
            //TODO: error handling?!
            val verificationSession = sdk.myKycSessions.first()
            if (verificationSession.verificationStatus == VerificationStatus.PROCESSING) {
                verificationSession.resumeWhenIdentified()
            } else if (verificationSession.verificationStatus == VerificationStatus.NOT_VERIFIED) {
                var identityResult = verificationSession.startIdentification(requireActivity())
                if (identityResult == IdentityFlowResult.COMPLETED) {
                    verificationSession.resumeWhenIdentified()
                }
            }
            navigateWithAction(ConfirmEmailFragmentDirections.toPersonaFragment())
        }
    }
}