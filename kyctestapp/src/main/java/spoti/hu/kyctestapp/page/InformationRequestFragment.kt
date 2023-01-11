package spoti.hu.kyctestapp.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kycdao.android.sdk.model.PersonalData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import spoti.hu.kyctestapp.R
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentInformationRequestBinding
import spoti.hu.kyctestapp.viewmodel.InformationRequestViewModel
import spoti.hu.kyctestapp.viewmodel.UIEvent

class InformationRequestFragment : BaseFragment<FragmentInformationRequestBinding>() {


	val viewModel: InformationRequestViewModel by viewModel()

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
		setupInputListener()
		setupPersonalDataHandling()
		listenToEvents()
		listenToButtonEnabled()
	}

	private fun setupDisclaimer() {
		binding.acceptDisclaimer.setOnCheckedChangeListener { _, isChecked ->
			viewModel.disclaimerAccepted.update { isChecked }
		}
	}

	private fun setupInputListener() {
		binding.emailInput.doOnTextChanged { text, _, _, _ -> viewModel.email.update { text.toString() } }


	}

	private fun listenToEvents() {
		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.eventBus.collect {
					when (it) {
						UIEvent.NavigateNext -> navigateNext()
					}
				}
			}
		}
	}

	private fun listenToButtonEnabled() {
		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.canProceed.collect {
					binding.continueProcess.apply {
						isEnabled = it
					}
				}
			}
		}
	}

	private fun setupPersonalDataHandling() {
		binding.continueProcess.setOnClickListener {
			viewModel.continueProcess()
		}
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
			binding.countryInput.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
				override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
					viewModel.residency.update { adapter.getItem(p2).toString() }
				}
				override fun onNothingSelected(p0: AdapterView<*>?) {
					return
				}

			}
		}
	}
}