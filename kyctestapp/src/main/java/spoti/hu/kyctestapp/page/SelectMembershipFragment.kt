package spoti.hu.kyctestapp.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentSelectMembershipBinding
import spoti.hu.kyctestapp.viewmodel.InformationRequestViewModel
import spoti.hu.kyctestapp.viewmodel.SelectMembershipViewModel
import java.math.BigInteger

class SelectMembershipFragment : BaseFragment<FragmentSelectMembershipBinding>() {

	private val viewModel: SelectMembershipViewModel by viewModel()


	override fun createBinding(
		inflater: LayoutInflater, container: ViewGroup?
	): FragmentSelectMembershipBinding {
		return FragmentSelectMembershipBinding.inflate(inflater, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setupSteppers()
		setupTexts()
		setupSelectMembership()
	}


	private fun setupSelectMembership() {
		binding.selectMembership.setOnClickListener {
			navigateWithAction(
				SelectMembershipFragmentDirections.toSelectNFTImageFragment(
					viewModel.periodInYear.value
				)
			)
		}
	}

	private fun setupSteppers() {
		binding.decreasePeriod.setOnClickListener {
			viewModel.changePeriod(-1)
		}

		binding.increasePeriod.setOnClickListener {
			viewModel.changePeriod(1)
		}
	}


	private fun setupTexts() {
		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.priceEstimation.collect { estimation ->
					binding.payment.text = if (estimation.paymentAmount > BigInteger.ZERO) {
						estimation.paymentAmountText
					} else {
						"Free"
					}
					binding.discount.text = if (estimation.discountYears > 0u) {
						"Discounted years applied: ${estimation.discountYears}"
					} else {
						"No discounts"
					}
				}
			}
		}
		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.perYearInUSD.collect{
					binding.price.text = "$it /year"
				}
			}
		}
		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.periodInYear.collect() { year ->
					binding.period.text = "$year year(s)"
					// Display yearly membership cost in dollars
					val cost = sdk.getVerificationSession().getMembershipCostPerYearText()
					binding.price.text = "$cost / year"
				}
			}
		}
	}
}