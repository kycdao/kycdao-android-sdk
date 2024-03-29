package spoti.hu.kyctestapp.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kycdao.android.sdk.model.VerificationType
import com.kycdao.android.sdk.util.Resource
import com.kycdao.android.sdk.verificationSession.KycDaoEnvironment
import com.kycdao.android.sdk.verificationSession.VerificationManager
import com.kycdao.android.sdk.wallet.WalletConnectManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentMainBinding
import timber.log.Timber

class MainFragment : BaseFragment<FragmentMainBinding>() {

	override fun createBinding(
		inflater: LayoutInflater,
		container: ViewGroup?
	): FragmentMainBinding {
		return FragmentMainBinding.inflate(inflater, container, false)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		Timber.plant(Timber.DebugTree());
		VerificationManager.configure(
			VerificationManager.Configuration(KycDaoEnvironment.Development)
		)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setupVerificationFlow()
		setupAddressTokenValidation()
		WalletConnectManager.startListening()

//        walletTokenValidation()
//        connectMetaMaskWallet()
//        legacyFunctions()
	}


	private fun setupAddressTokenValidation() {

		binding.addressHasValidToken.setOnClickListener {
			lifecycleScope.launch {
				val result = VerificationManager.hasValidToken(
					VerificationType.KYC,
					binding.polygonWalletAddress.text.toString(),
					"eip155:80001"
				)
				Toast.makeText(requireContext(), if(result) "This address has a valid token" else "This address DOES NOT have a valid token", Toast.LENGTH_LONG).show()
			}
		}
	}
//
//    private fun connectMetaMaskWallet() {
//    }
//
//    private fun walletTokenValidation() {
//        binding.walletHasValidToken.setOnClickListener {
//            val walletAddress = sdk.getWalletConnectSession()?.getAvailableWallets()?.first()
//                ?: throw Exception("No wallet found")
//            lifecycleScope.launch {
//                binding.walletTokenValidity.text = VerificationManager.hasValidToken(
//                    VerificationType.KYC,
//                    walletAddress,
//                    sdk.getWalletConnectSession()!!
//                ).toString()
//            }
//        }
//    }

	private fun setupVerificationFlow() {
		lifecycleScope.launch{
			repeatOnLifecycle(Lifecycle.State.CREATED) {
				WalletConnectManager.sessionsState.collect { result ->
					when (result) {
						is Resource.Failure -> {
							Timber.d("FAIL wc")
						}
						is Resource.Success -> {
							Timber.d("SUCCESS wc")
							sdk.saveWalletConnectSession(result.data)
							delay(500)
							WalletConnectManager.stopListening()
							navigateWithAction(MainFragmentDirections.actionMainFragmentToWalletConnectedFragment())
						}
					}
				}
			}
		}

		binding.startVerificationFlow.setOnClickListener {
			WalletConnectManager.connectWallet()
		}
	}

//    private fun legacyFunctions() {
//        lifecycleScope.launchWhenCreated {
//            sdk.myWalletSession.collect {
//                binding.connectWallet.text = if (it != null) {
//                    "wallet connected"
//                } else {
//                    "connect"
//                }
//            }
//        }
//        lifecycleScope.launchWhenCreated {
//            WalletConnectManager.wcURI.collect {
//                // Initializing the QR Encoder with your value to be encoded, type you required and Dimension
//                // Initializing the QR Encoder with your value to be encoded, type you required and Dimension
//                val qrgEncoder =
//                    QRGEncoder(it, null, QRGContents.Type.TEXT, 400)
//                qrgEncoder.colorBlack = Color.WHITE
//                qrgEncoder.colorWhite = Color.BLACK
//                try {
//                    // Getting QR-Code as Bitmap
//                    val bitmap = qrgEncoder.bitmap
//                    // Setting Bitmap to ImageView
//                    binding.qrImageView.setImageBitmap(bitmap)
//                } catch (e: Exception) {
//
//                }
//            }
//        }
//
//        lifecycleScope.launchWhenCreated {
//            WalletConnectManager.sessionsState.collect { result ->
//                when (result) {
//                    is Resource.Failure -> {
//                        Timber.d("FAIL wc")
//                        //WalletConnectManager.startListening()
//                    }
//                    is Resource.Success -> {
//                        Timber.d("SUCCESS wc")
//                        sdk.myWalletSession.update { result.data }
//                    }
//                }
//
//            }
//        }
//
//        binding.acceptDisclaimer.setOnClickListener {
//            lifecycleScope.launch {
//                sdk.getVerificationSession().acceptDisclaimer()
//            }
//        }
//        binding.getPerYear.setOnClickListener {
//            lifecycleScope.launch {
//                val res = sdk.getVerificationSession().getMembershipCostPerYear()
//                Timber.d("$res $")
//            }
//        }
//        binding.estimateCost.setOnClickListener {
//            lifecycleScope.launch {
//                val res = sdk.getVerificationSession().estimatePayment(3u)
//                Timber.d(res.paymentAmountText)
//            }
//        }
//        binding.hasTokenValid.setOnClickListener {
//            val walletAddress = sdk.getWalletConnectSession()?.getAvailableWallets()?.first()
//                ?: throw Exception("No wallet found")
//            lifecycleScope.launch {
//                binding.hasTokenValid.text = VerificationManager.hasValidToken(
//                    VerificationType.KYC,
//                    walletAddress,
//                    sdk.getWalletConnectSession()!!
//                ).toString()
//            }
//        }
//        binding.connectWallet.setOnClickListener {
//            WalletConnectManager.connectWallet()
//        }
//        binding.createKyc.setOnClickListener {
//            val walletAddress = sdk.getWalletConnectSession()?.getAvailableWallets()?.first()
//                ?: throw Exception("No wallet found")
//            lifecycleScope.launch {
//                sdk.myKycSessions.add(
//                    VerificationManager.createSession(
//                        walletAddress,
//                        sdk.getWalletConnectSession()!!
//                    )
//                )
//            }
//        }
//        binding.login.setOnClickListener {
//            lifecycleScope.launch {
//                sdk.getVerificationSession().login()
//                println("userID: ${sdk.getVerificationSession().loggedIn}")
//            }
//        }
//        binding.addPersonalInfoBtn.setOnClickListener {
//            val mockPersonalInfo = PersonalData(
//                email = "adamszucs2000@gmail.com",
//                residency = "HUN",
//                isLegalEntity = false
//            )
//            lifecycleScope.launch {
//                sdk.getVerificationSession().setPersonalData(mockPersonalInfo)
//                sdk.getVerificationSession().resumeOnEmailConfirmed()
//            }
//        }
//        binding.startPersona.setOnClickListener {
//            lifecycleScope.launch {
//                sdk.getVerificationSession().startIdentification(requireActivity())
//            }
//        }
//        binding.selectNFT.setOnClickListener {
//            lifecycleScope.launch {
//                val images = sdk.getVerificationSession().getNFTImages()
//                sdk.getVerificationSession().requestMinting(images.first().id, 3u)
//            }
//        }
//        binding.mintNFT.setOnClickListener {
//            lifecycleScope.launch {
//                val url = sdk.getVerificationSession().mint()
//                println("probably done : $url")
//            }
//        }
//        binding.resendEmail.setOnClickListener {
//            lifecycleScope.launch {
//                sdk.getVerificationSession().resendConfirmationEmail()
//            }
//        }
//    }

}