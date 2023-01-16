package spoti.hu.kyctestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import spoti.hu.kyctestapp.manager.SDKManager

class SelectMembershipViewModel(
	private val sdkManager: SDKManager
) : ViewModel() {
	var perYearInUSD = MutableStateFlow("")
	init {
		viewModelScope.launch {
			perYearInUSD.update {
				sdkManager.getVerificationSession().getMembershipCostPerYearText()
			}
		}
	}
	val periodInYear = MutableStateFlow(1)
	fun changePeriod(amount: Int){
		periodInYear.update { (it+amount).let { value -> if(value>0) value else it } }
	}

	val priceEstimation = periodInYear.map {
		sdkManager.getVerificationSession().estimatePayment(it)
	}
}