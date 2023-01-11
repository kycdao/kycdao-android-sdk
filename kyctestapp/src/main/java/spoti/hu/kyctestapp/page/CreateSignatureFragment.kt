package spoti.hu.kyctestapp.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bitraptors.networking.api.models.NetworkErrorResponse
import com.kycdao.android.sdk.exceptions.KycNetworkCallException
import com.kycdao.android.sdk.model.KYCErrorResponse
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentCreateSignatureBinding
import timber.log.Timber

class CreateSignatureFragment : BaseFragment<FragmentCreateSignatureBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCreateSignatureBinding {
        return FragmentCreateSignatureBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSignatureCreation()
    }

    private fun setupSignatureCreation() {
        binding.login.setOnClickListener {
            lifecycleScope.launchWhenResumed {
                try {
                    sdk.getVerificationSession().login()
                    Timber.d("userID: ${sdk.getVerificationSession().loggedIn}")
                    navigateToInformationRequestPage()
                } catch (e: KycNetworkCallException) {
                    e.printStackTrace()
                    if (e.networkException is NetworkErrorResponse.ApiError && (e.networkException as NetworkErrorResponse.ApiError<KYCErrorResponse>).code == 400) {
                        navigateToInformationRequestPage()
                    }
                }

            }
        }
    }

    private fun navigateToInformationRequestPage() {
        navigateNext()
    }
}

