package spoti.hu.kyctestapp.page

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kycdao.android.sdk.model.VerificationStatus
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentConfirmEmailBinding
import spoti.hu.kyctestapp.viewmodel.ActionResult
import spoti.hu.kyctestapp.viewmodel.ConfirmEmailViewModel
import spoti.hu.kyctestapp.viewmodel.InformationRequestViewModel

class ConfirmEmailFragment : BaseFragment<FragmentConfirmEmailBinding>() {
    private val viewModel: ConfirmEmailViewModel by viewModel()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentConfirmEmailBinding {
        return FragmentConfirmEmailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupResend()
        setupChangeEmail()
        setupCheckEmailConfirm()
        viewModel.loadCurrentEmail()
        listenToState()
    }

    private fun listenToState() {
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.email.collect{
                    binding.emailAddress.editText?.setText(it)
                }
            }
        }
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.actionResult.collect{
                   binding.emailAddress.boxStrokeColor = when(it){
                        ActionResult.Success -> Color.GREEN
                        ActionResult.Fail -> Color.RED
                        ActionResult.Loading -> Color.YELLOW
                        null -> Color.CYAN
                    }
                }
            }
        }
    }

    private fun setupChangeEmail() {
        binding.changeEmail.setOnClickListener {
            viewModel.changeEmail(binding.emailAddress.editText?.text.toString())
        }
    }

    private fun setupCheckEmailConfirm() {
        lifecycleScope.launch {
            sdk.getVerificationSession().resumeOnEmailConfirmed()
            navigateNext()
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