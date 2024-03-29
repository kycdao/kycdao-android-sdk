package spoti.hu.kyctestapp.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.kycdao.android.sdk.model.IdentityFlowResult
import kotlinx.coroutines.launch
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentPersonaBinding
import timber.log.Timber

class PersonaFragment : BaseFragment<FragmentPersonaBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPersonaBinding {
        return FragmentPersonaBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupStartPersona()
    }


    private fun setupStartPersona() {
        binding.startPersona.setOnClickListener {
            lifecycleScope.launch {
                when (sdk.getVerificationSession().startIdentification(requireActivity())) {
                    IdentityFlowResult.COMPLETED -> {
                        Timber.d("Navigate next")
                        navigateNext()
                    }
                    IdentityFlowResult.CANCELLED -> {
                        Timber.d("Caneceled")
                    }
                }
            }
        }
    }
}