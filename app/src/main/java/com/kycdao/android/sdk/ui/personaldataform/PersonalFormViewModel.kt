package com.kycdao.android.sdk.ui.personaldataform

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import java.util.*

data class CountryModel(val countryKey: String, val countryName: String)

class PersonalFormViewModel : ViewModel() {

    val dropDownItems: List<CountryModel> = Locale.getAvailableLocales()
        .filter { it.country.isNotBlank() && it.displayCountry.isNotBlank() }
        .filter { !it.country.isDigitsOnly() } // filter continents
        .distinctBy { it.displayCountry }
        .map { CountryModel(countryKey = it.country, countryName = it.displayCountry) }
        .sortedBy { it.countryName }
//        .map { it.also { Log.d("CountryModel", it.toString()) } }

    val isLegalUserChecked = MutableStateFlow(false)
    val isAccepted = MutableStateFlow(false)
    val emailField = MutableStateFlow("")
    val countryField = MutableStateFlow("")

    val isButtonEnabled = combine(isAccepted, emailField, countryField)
    { accept, email, country ->
        accept && email.isNotBlank() && country.isNotBlank()
    }.asLiveData()
}

fun String.isEmail(handler : (Boolean) -> Unit) {
    if (this.isNotBlank() && this.contains("@")) {
        if (this.split("@")[1].contains(".")) {
            handler(this.split("@")[1].split(".").last().isNotBlank())
        } else {
            handler(false)
        }
    } else {
        handler(false)
    }
}