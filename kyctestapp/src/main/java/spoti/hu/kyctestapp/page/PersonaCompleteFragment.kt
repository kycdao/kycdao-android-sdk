package spoti.hu.kyctestapp.page

import android.view.LayoutInflater
import android.view.ViewGroup
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentPersonaCompleteBinding

class PersonaCompleteFragment : BaseFragment<FragmentPersonaCompleteBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPersonaCompleteBinding {
        return FragmentPersonaCompleteBinding.inflate(inflater, container, false)
    }
}