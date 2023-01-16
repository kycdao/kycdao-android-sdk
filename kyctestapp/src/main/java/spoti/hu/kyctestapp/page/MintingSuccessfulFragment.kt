package spoti.hu.kyctestapp.page

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentMintingSuccesfulBinding


class MintingSuccessfulFragment : BaseFragment<FragmentMintingSuccesfulBinding>() {
    private val args by navArgs<MintingSuccessfulFragmentArgs>()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMintingSuccesfulBinding {
        return FragmentMintingSuccesfulBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.transaction.setOnClickListener {
            val url = args.explorerUrl
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        binding.exit.setOnClickListener {
            navigateWithAction(MintingSuccessfulFragmentDirections.actionMintingSuccessfulFragmentToWalletConnectedFragment())
        }
    }
}