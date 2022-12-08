package spoti.hu.kyctestapp.page

import android.view.LayoutInflater
import android.view.ViewGroup
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentInformationRequestBinding

class InformationRequestFragment : BaseFragment<FragmentInformationRequestBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInformationRequestBinding {
        return FragmentInformationRequestBinding.inflate(inflater, container, false)
    }
}