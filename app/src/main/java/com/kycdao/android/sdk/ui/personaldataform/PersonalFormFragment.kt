package com.kycdao.android.sdk.ui.personaldataform

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.reflect.TypeToken
import com.kycdao.android.sdk.model.PersonalDataResult
import com.kycdao.android.sdk.ui.KycActivityResultContract
import com.kycdao.android.sdk.ui.base.BaseFragment
import com.kycdao.android.sdk.ui.hideKeyboard
import com.kycdao.android.sdk.ui.setButtonEnabler
import kycdao.android.sdk.R
import kycdao.android.sdk.databinding.FragmentPersonalFormBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonalFormFragment : BaseFragment<Nothing>() {

    companion object {
        @JvmStatic
        fun newInstance() = PersonalFormFragment()
    }

    private lateinit var binding: FragmentPersonalFormBinding

    private val viewModel: PersonalFormViewModel by viewModel()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonalFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonEnabler(binding.acceptButton, viewModel.isButtonEnabled)
        setButtonCheckBoxObservers()
        setListItems()
        setInputObservers()
        setContinueButton()
    }

    private fun setListItems() {
        val items = viewModel.dropDownItems.map { it.countryName }
        val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, items)
        binding.countryField.setDropDownBackgroundResource(R.color.fab_color)
        binding.countryField.setAdapter(adapter)
        binding.countryField.setOnItemClickListener { _, _, _, _ ->
            binding.countrySelectorRoot.updateError()
            hideKeyboard()
        }
    }

    private fun setContinueButton() {
        binding.acceptButton.setOnClickListener {
            checkEmailFormat(binding.emailField.text.toString(), binding.emailFieldRoot) { valid ->

                val currentCountryText = binding.countryField.text.toString()
                val currentCountry = viewModel.dropDownItems.find { it.countryName == currentCountryText }

                if (currentCountry == null) {
                    binding.countrySelectorRoot.updateError("Invalid country")
                } else {
                    sendData(currentCountry)
                }
            }
        }
    }

    private fun checkEmailFormat(
        email: String,
        input: TextInputLayout,
        handler: (Boolean) -> Unit
    ) {
        email.isEmail { isEmail ->
            if (isEmail) {
                input.updateError()
            } else {
                input.updateError("Invalid email format")
            }
            handler(isEmail)
        }
    }

    private fun sendData(residency: CountryModel) {
        requireActivity().setResult(Activity.RESULT_OK, Intent().apply {
            putExtra(KycActivityResultContract.RESULT_KEY, personalDataResult(residency))
        })
        requireActivity().finish()
    }

    private fun personalDataResult(residency: CountryModel) : PersonalDataResult {
        return PersonalDataResult(
            email = binding.emailField.text.toString(),
            residency = residency.countryKey,
            isLegalEntity = binding.legalUserBox.isChecked
        )
    }

    private fun TextInputLayout.updateError(text: String? = null) {
        isErrorEnabled = !text.isNullOrBlank()
        error = text
    }

    private fun setButtonCheckBoxObservers() {
        binding.legalUserBox.setOnCheckedChangeListener { _, checked ->
            viewModel.isLegalUserChecked.tryEmit(checked)
        }
        binding.acceptBox.setOnCheckedChangeListener { _, checked ->
            viewModel.isAccepted.tryEmit(checked)
        }
    }

    private fun setInputObservers() {
        binding.emailField.setText(viewModel.emailField.value)
        binding.countryField.setText(viewModel.countryField.value)

        binding.countryField.addTextChangedListener {
            viewModel.countryField.tryEmit(it.toString())
        }

        binding.emailField.addTextChangedListener {
            viewModel.emailField.tryEmit(it.toString())
        }
    }
}