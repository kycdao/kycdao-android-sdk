package spoti.hu.kyctestapp.page

import android.view.LayoutInflater
import android.view.ViewGroup
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentMainBinding
import spoti.hu.kyctestapp.databinding.FragmentMintingInProgressBinding

class MintingInProgressFragment : BaseFragment<FragmentMintingInProgressBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMintingInProgressBinding {
        return FragmentMintingInProgressBinding.inflate(inflater, container, false)
    }
}