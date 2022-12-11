package spoti.hu.kyctestapp.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentSelectMembershipBinding
import java.math.BigInteger

class SelectMembershipFragment : BaseFragment<FragmentSelectMembershipBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSelectMembershipBinding {
        return FragmentSelectMembershipBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSteppers()
        setupTexts()
        setupSelectMembership()
    }

    private val selectedDuration = MutableStateFlow<Int>(1)

    private fun setupSelectMembership() {
        binding.selectMembership.setOnClickListener {
            navigateWithAction(SelectMembershipFragmentDirections.toSelectNFTImageFragment())
        }
    }

    private fun setupSteppers() {
        binding.decreasePeriod.setOnClickListener {
            selectedDuration.value =
                if (selectedDuration.value > 0) selectedDuration.value-- else 0
        }

        binding.increasePeriod.setOnClickListener {
            selectedDuration.value =
                if (selectedDuration.value == 100) 100 else selectedDuration.value++
        }
    }


    private fun setupTexts() {
        lifecycleScope.launch {
            selectedDuration.collect() { year ->
                val verificationSession = sdk.myKycSessions.first()
                // Display yearly membership cost in dollars
                val cost = verificationSession.getMembershipCostPerYear()
                binding.price.text = "$cost / year"

                val paymentEstimation = verificationSession.estimatePayment(year.toUInt())

                if (paymentEstimation.paymentAmount > BigInteger.valueOf(0)) {
                    binding.payment.text = paymentEstimation.paymentAmountText
                } else {
                    binding.payment.text = "Free"
                }

                if (paymentEstimation.discountYears > 0u) {
                    binding.discount.text =
                        "Discounted years applied: ${paymentEstimation.discountYears}"
                } else {
                    binding.discount.text = "No discounts"
                }
            }
        }
    }
}