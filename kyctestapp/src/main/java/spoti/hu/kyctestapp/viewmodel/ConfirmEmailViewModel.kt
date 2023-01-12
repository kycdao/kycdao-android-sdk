package spoti.hu.kyctestapp.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import spoti.hu.kyctestapp.manager.SDKManager

class ConfirmEmailViewModel(
	private val sdkManager: SDKManager
) : ViewModel(){
	val email = MutableStateFlow("")
	val actionResult = MutableStateFlow<ActionResult?>(null)
	fun loadCurrentEmail(){
		email.update { sdkManager.getVerificationSession().currentEmail.toString() }

	}
	fun changeEmail(newEmail: String){
		actionResult.update { ActionResult.Loading }
		val oldEmail = sdkManager.getVerificationSession().currentEmail
		if(newEmail != oldEmail && Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()){
			viewModelScope.launch {
				sdkManager.getVerificationSession().updateEmail(newEmail)
				actionResult.update { ActionResult.Success }
				loadCurrentEmail()
			}
		}else{
			actionResult.update { ActionResult.Fail }
		}
	}
}

enum class ActionResult{
	Success, Fail, Loading
}
