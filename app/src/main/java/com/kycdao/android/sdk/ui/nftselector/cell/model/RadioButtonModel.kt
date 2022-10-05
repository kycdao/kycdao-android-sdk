package com.kycdao.android.sdk.ui.nftselector.cell.model

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RadioButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.kycdao.android.sdk.ui.loadSvgWithCookie
import kycdao.android.sdk.databinding.ViewRadioButtonBinding
import okhttp3.CookieJar
import org.koin.java.KoinJavaComponent.get

sealed class RadioButtonModel(
    open val id: String,
    open val idx: Int,
    open val isSelected: Boolean = false,
    open val imageUrl: String,
) {
    //isSelected is changed on UI, so it is does not need updating
    fun getContentString(): String {
        return id + imageUrl
    }
}

class RadioButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val cookieJar = get<CookieJar>(CookieJar::class.java)

    private var binding: ViewRadioButtonBinding =
        ViewRadioButtonBinding.inflate(LayoutInflater.from(context), this, true)

    fun setContent(
        radioButtonModel: RadioButtonModel,
        onClickListener : ()-> Unit
    ) {
        binding.apply {
            image.loadSvgWithCookie(radioButtonModel.imageUrl, cookieJar)
            radioButton.setOnClickListener {
                onClickListener()
            }
        }
        setOnClickListener {
            onClickListener()
        }

        if(radioButtonModel.isSelected) select()
    }

    fun select(){
        binding.radioButton.check()
    }

    fun deselect(){
        binding.radioButton.unCheck()
    }

    fun selected(): Boolean{
        return binding.radioButton.isChecked
    }

}

fun RadioButton.unCheck(){
    if(this.isChecked){
        this.isChecked = false
    }
}

fun RadioButton.check(){
    if(!this.isChecked){
        this.isChecked = true
    }
}