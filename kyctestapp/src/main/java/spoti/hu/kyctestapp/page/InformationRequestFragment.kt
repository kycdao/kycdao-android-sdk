package spoti.hu.kyctestapp.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.kycdao.android.sdk.model.PersonalData
import kotlinx.coroutines.launch
import spoti.hu.kyctestapp.R
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentInformationRequestBinding

class InformationRequestFragment : BaseFragment<FragmentInformationRequestBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInformationRequestBinding {
        return FragmentInformationRequestBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpinner()
        setupDisclaimer()
        setupPersonalDataHandling()
    }

    private fun setupDisclaimer() {
        binding.acceptDisclaimer.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                if (isChecked) {
                    sdk.getVerificationSession().acceptDisclaimer()
                }
            }
        }
    }

    private fun setupPersonalDataHandling() {
        binding.continueProcess.setOnClickListener {
            if (checkIfEverythingIsFilledOut()) {
                val mockPersonalInfo = PersonalData(
                    email = binding.emailInput.text.toString(),
                    residency = binding.countryInput.selectedItem.toString(),
                    isLegalEntity = false
                )
                lifecycleScope.launch {
                    sdk.getVerificationSession().setPersonalData(mockPersonalInfo)
                    navigateWithAction(InformationRequestFragmentDirections.toConfirmEmailFragment())
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please fill out your personal data and accept the disclaimer.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun checkIfEverythingIsFilledOut(): Boolean {
        return binding.emailInput.text?.isNotBlank() == true && binding.acceptDisclaimer.isChecked
    }


    private fun setupSpinner() {

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.country_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.countryInput.adapter = adapter
        }
    }
}