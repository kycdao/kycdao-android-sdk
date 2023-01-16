package spoti.hu.kyctestapp.page

import android.view.LayoutInflater
import android.view.ViewGroup
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentAuthorizeMintingBinding

class AuthorizeMintingFragment : BaseFragment<FragmentAuthorizeMintingBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAuthorizeMintingBinding {
        return FragmentAuthorizeMintingBinding.inflate(inflater, container, false)
    }
}