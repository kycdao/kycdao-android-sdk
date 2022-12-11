package spoti.hu.kyctestapp.page

import android.view.LayoutInflater
import android.view.ViewGroup
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentMintNftBinding

class MintNFTFragment : BaseFragment<FragmentMintNftBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMintNftBinding {
        return FragmentMintNftBinding.inflate(inflater, container, false)
    }
}