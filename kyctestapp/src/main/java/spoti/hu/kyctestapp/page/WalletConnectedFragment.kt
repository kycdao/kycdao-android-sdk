package spoti.hu.kyctestapp.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kycdao.android.sdk.verificationSession.VerificationManager
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentWalletConnectedBinding
import spoti.hu.kyctestapp.viewmodel.WalletConnectedViewModel

class WalletConnectedFragment : BaseFragment<FragmentWalletConnectedBinding>() {

	private val viewModel: WalletConnectedViewModel by viewModel()

	override fun createBinding(
		inflater: LayoutInflater,
		container: ViewGroup?
	): FragmentWalletConnectedBinding {
		return  FragmentWalletConnectedBinding.inflate(inflater,container,false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setupStartVerification()
		setupWalletCheck()
		setupCheckVerification()
		listenToWalletID()
		listenToWalletCheckResult()
		listenToCheckVerificationResult()
	}

	private fun listenToCheckVerificationResult() {
		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED){
				viewModel.verifiedNetworksMap.collect{
					binding.checkVerificationMapResult.text = it.toString()
				}
			}
		}
	}

	private fun setupCheckVerification() {
		binding.checkVerification.setOnClickListener {
			lifecycleScope.launch {
				viewModel.checkVerifications()
			}
		}
	}

	private fun listenToWalletCheckResult() {
		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED){
				viewModel.walletHasToken.collect{
					binding.walletCheckResultLabel.text = when(it) {
						true -> "Wallet has token"
						false -> "Wallet does not have token"
						null -> "..."
					}
				}
			}
		}
	}

	private fun setupWalletCheck() {
		binding.checkWallet.setOnClickListener {
			viewModel.checkWalletToken()
		}
	}

	private fun listenToWalletID() {
		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED){
				viewModel.connectedWalletId.collect{
					binding.walletIdLabel.text = it
				}
			}
		}
	}

	private fun setupStartVerification() {
		binding.startVerificationBtn.setOnClickListener {
			lifecycleScope.launch {
				try {
					val walletSession = sdk.getWalletConnectSession()
					sdk.saveVerificationSession(
						VerificationManager.createSession(
							walletSession.getAvailableWallets()?.first()
								?: throw Exception("No wallet found"),
							sdk.getWalletConnectSession()
						)
					)
					navigateNext()
				} catch (e: Exception) {
					Toast.makeText(
						requireContext(),
						"Create session failed!",
						Toast.LENGTH_SHORT
					).show()
				}
			}
		}
	}
}