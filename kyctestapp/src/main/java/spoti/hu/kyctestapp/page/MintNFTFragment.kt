package spoti.hu.kyctestapp.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import kotlinx.coroutines.launch
import spoti.hu.kyctestapp.R
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentMintNftBinding

class MintNFTFragment : BaseFragment<FragmentMintNftBinding>() {

    private val args by navArgs<MintNFTFragmentArgs>()

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
        setupNFTImage()
    }

    private fun setupNFTImage(){
        binding.artwork.load(args.url)
    }

    private fun setupText() {
        lifecycleScope.launch {
            val mintingPrice = sdk.getVerificationSession().getMintingPrice()
            binding.fee.text = mintingPrice.fullPriceText
        }
    }

    private fun setupMint() {
        binding.mintNFT.setOnClickListener {
            lifecycleScope.launch {
                showMinting()
                try {
                    val mintingResult = sdk.getVerificationSession().mint()
                    navigateWithAction(MintNFTFragmentDirections.toMintingSuccessfulFragment(mintingResult.explorerURL.toString()))
                } catch (e: Exception) {
                    dismissMinting()
                }
            }
        }
    }

    private fun showMinting() {
        childFragmentManager.beginTransaction()
            .add(R.id.loaderContainer, AuthorizeMintingFragment()).commit()
    }

    private fun dismissMinting() {
        childFragmentManager.popBackStack()
    }
}