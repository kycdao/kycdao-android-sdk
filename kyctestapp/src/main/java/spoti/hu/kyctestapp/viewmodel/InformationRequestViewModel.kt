package spoti.hu.kyctestapp.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kycdao.android.sdk.model.PersonalData
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import spoti.hu.kyctestapp.manager.SDKManager

class InformationRequestViewModel(
	private val sdkManager: SDKManager
) : ViewModel() {

	 val disclaimerAccepted = MutableStateFlow(false)

	 val email = MutableStateFlow("")
	 val residency = MutableStateFlow("")

	val eventBus : MutableSharedFlow<UIEvent> = MutableSharedFlow(extraBufferCapacity = 1)

	val canProceed = combine(disclaimerAccepted, email, residency){ disclaimerAccepted, email, residency ->
		disclaimerAccepted && email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches() && residency.isNotEmpty()
	}.distinctUntilChanged()

	fun continueProcess(){
		val session = sdkManager.getVerificationSession()
		viewModelScope.launch {
			session.acceptDisclaimer()
			val personalInfo = PersonalData(
				email = email.value,
				residency = residency.value,
			)
			session.setPersonalData(personalInfo)
			eventBus.tryEmit(UIEvent.NavigateNext)
		}
	}
}


sealed class UIEvent{
	object NavigateNext: UIEvent()
}