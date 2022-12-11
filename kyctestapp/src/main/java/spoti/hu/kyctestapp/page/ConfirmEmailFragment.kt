package spoti.hu.kyctestapp.page

import android.view.LayoutInflater
import android.view.ViewGroup
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentConfirmEmailBinding

class ConfirmEmailFragment : BaseFragment<FragmentConfirmEmailBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentConfirmEmailBinding {
        return FragmentConfirmEmailBinding.inflate(inflater, container, false)
    }
}