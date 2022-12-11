package spoti.hu.kyctestapp.page

import android.view.LayoutInflater
import android.view.ViewGroup
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentMintingSuccesfulBinding

class MintingSuccessfulFragment : BaseFragment<FragmentMintingSuccesfulBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMintingSuccesfulBinding {
        return FragmentMintingSuccesfulBinding.inflate(inflater, container, false)
    }
}