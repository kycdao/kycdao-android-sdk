package spoti.hu.kyctestapp.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.kycdao.android.sdk.model.VerificationStatus
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
        lifecycleScope.launchWhenResumed {
            val verificationSession = sdk.myKycSessions.first()
            if (verificationSession.verificationStatus == VerificationStatus.NOT_VERIFIED) {
                //TODO: sign that it is a retry!
                navigateWithAction(PersonaCompleteFragmentDirections.toPersonaFragment())
            } else {
                //TODO: error handling?!
                verificationSession.resumeWhenIdentified()
                navigateWithAction(PersonaCompleteFragmentDirections.toSelectMembershipFragment())
            }
        }
    }
}