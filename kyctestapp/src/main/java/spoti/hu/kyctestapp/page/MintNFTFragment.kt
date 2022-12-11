package spoti.hu.kyctestapp.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentMintNftBinding

class MintNFTFragment : BaseFragment<FragmentMintNftBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMintNftBinding {
        return FragmentMintNftBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupText()
        setupMint()
    }


    private fun setupText() {
        lifecycleScope.launch {
            val verificationSession = sdk.myKycSessions.first()
            var mintingPrice = verificationSession.getMintingPrice()
            binding.fee.text = mintingPrice.fullPriceText
        }
    }

    private fun setupMint() {
        binding.mintNFT.setOnClickListener {
            lifecycleScope.launch {
                val url = sdk.myKycSessions.first().mint()
                navigateWithAction(MintNFTFragmentDirections.toMintingSuccessfulFragment())
            }
        }
    }
}