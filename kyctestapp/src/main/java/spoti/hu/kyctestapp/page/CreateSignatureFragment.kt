package spoti.hu.kyctestapp.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
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
            lifecycleScope.launch {
                sdk.myKycSessions.first().login()
                Timber.d("userID: ${sdk.myKycSessions.first().loggedIn}")
                navigateWithAction(CreateSignatureFragmentDirections.toInformationRequestFragment())
            }
        }
    }
}

