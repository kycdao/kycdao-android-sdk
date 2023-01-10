package spoti.hu.kyctestapp.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.kycdao.android.sdk.model.VerificationStatus
import kotlinx.coroutines.launch
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentConfirmEmailBinding

class ConfirmEmailFragment : BaseFragment<FragmentConfirmEmailBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentConfirmEmailBinding {
        return FragmentConfirmEmailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupResend()
        setupCheckEmailConfirm()
    }


    private fun setupCheckEmailConfirm() {
        lifecycleScope.launch {
            sdk.getVerificationSession().resumeOnEmailConfirmed()

            val verificationSession = sdk.getVerificationSession()
            if (verificationSession.verificationStatus == VerificationStatus.VERIFIED) {
                navigateWithAction(ConfirmEmailFragmentDirections.toSelectMembershipFragment())
            }else{
                navigateWithAction(ConfirmEmailFragmentDirections.toPersonaFragment())
            }
        }
    }

    private fun setupResend() {
        binding.resendEmail.setOnClickListener {
            lifecycleScope.launch {
                sdk.getVerificationSession().resendConfirmationEmail()
            }
        }
    }

}