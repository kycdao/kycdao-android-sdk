package spoti.hu.kyctestapp.page

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.lifecycle.lifecycleScope
import com.kycdao.android.sdk.model.PersonalData
import com.kycdao.android.sdk.model.VerificationType
import com.kycdao.android.sdk.util.Resource
import com.kycdao.android.sdk.verificationSession.KycDaoEnvironment
import com.kycdao.android.sdk.verificationSession.VerificationManager
import com.kycdao.android.sdk.wallet.WalletConnectManager
import kotlinx.coroutines.flow.update
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
        Timber.d("CREATE")
        VerificationManager.configure(
            VerificationManager.Configuration(KycDaoEnvironment.Development)
        )
        WalletConnectManager.startListening()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupVerificationFlow()
        setupAddressTokenValidation()
//        walletTokenValidation()
//        connectMetaMaskWallet()
//        legacyFunctions()
    }

    private fun setupAddressTokenValidation() {
        binding.addressHasValidToken.setOnClickListener {
            val walletAddress = sdk.myWalletSession.value?.getAvailableWallets()?.first()
                ?: throw Exception("No wallet found")
            lifecycleScope.launch {
                val text = VerificationManager.hasValidToken(
                    VerificationType.KYC,
                    walletAddress,
                    sdk.myWalletSession.value!!
                ).toString()
                Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
            }
        }
    }
//
//    private fun connectMetaMaskWallet() {
//        //TODO: How we should put this without interfering with another
//    }
//
//    private fun walletTokenValidation() {
//        binding.walletHasValidToken.setOnClickListener {
//            val walletAddress = sdk.myWalletSession.value?.getAvailableWallets()?.first()
//                ?: throw Exception("No wallet found")
//            lifecycleScope.launch {
//                binding.walletTokenValidity.text = VerificationManager.hasValidToken(
//                    VerificationType.KYC,
//                    walletAddress,
//                    sdk.myWalletSession.value!!
//                ).toString()
//            }
//        }
//    }

    private fun setupVerificationFlow() {
        lifecycleScope.launchWhenCreated {
            WalletConnectManager.sessionsState.collect { result ->
                when (result) {
                    is Resource.Failure -> {
                        Timber.d("FAIL wc")
                        //WalletConnectManager.startListening()
                    }
                    is Resource.Success -> {
                        Timber.d("SUCCESS wc")
                        sdk.myWalletSession.update { result.data }

                        try {
                            sdk.myKycSessions.add(
                                VerificationManager.createSession(
                                    result.data.getAvailableWallets()?.first()
                                        ?: throw Exception("No wallet found"),
                                    sdk.myWalletSession.value!!
                                )
                            )
                            navigateWithAction(MainFragmentDirections.toCreateSignatureFragment())
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
//                sdk.myKycSessions.first().acceptDisclaimer()
//            }
//        }
//        binding.getPerYear.setOnClickListener {
//            lifecycleScope.launch {
//                val res = sdk.myKycSessions.first().getMembershipCostPerYear()
//                Timber.d("$res $")
//            }
//        }
//        binding.estimateCost.setOnClickListener {
//            lifecycleScope.launch {
//                val res = sdk.myKycSessions.first().estimatePayment(3u)
//                Timber.d(res.paymentAmountText)
//            }
//        }
//        binding.hasTokenValid.setOnClickListener {
//            val walletAddress = sdk.myWalletSession.value?.getAvailableWallets()?.first()
//                ?: throw Exception("No wallet found")
//            lifecycleScope.launch {
//                binding.hasTokenValid.text = VerificationManager.hasValidToken(
//                    VerificationType.KYC,
//                    walletAddress,
//                    sdk.myWalletSession.value!!
//                ).toString()
//            }
//        }
//        binding.connectWallet.setOnClickListener {
//            WalletConnectManager.connectWallet()
//        }
//        binding.createKyc.setOnClickListener {
//            val walletAddress = sdk.myWalletSession.value?.getAvailableWallets()?.first()
//                ?: throw Exception("No wallet found")
//            lifecycleScope.launch {
//                sdk.myKycSessions.add(
//                    VerificationManager.createSession(
//                        walletAddress,
//                        sdk.myWalletSession.value!!
//                    )
//                )
//            }
//        }
//        binding.login.setOnClickListener {
//            lifecycleScope.launch {
//                sdk.myKycSessions.first().login()
//                println("userID: ${sdk.myKycSessions.first().loggedIn}")
//            }
//        }
//        binding.addPersonalInfoBtn.setOnClickListener {
//            val mockPersonalInfo = PersonalData(
//                email = "adamszucs2000@gmail.com",
//                residency = "HUN",
//                isLegalEntity = false
//            )
//            lifecycleScope.launch {
//                sdk.myKycSessions.first().setPersonalData(mockPersonalInfo)
//                sdk.myKycSessions.first().resumeOnEmailConfirmed()
//            }
//        }
//        binding.startPersona.setOnClickListener {
//            lifecycleScope.launch {
//                sdk.myKycSessions.first().startIdentification(requireActivity())
//            }
//        }
//        binding.selectNFT.setOnClickListener {
//            lifecycleScope.launch {
//                val images = sdk.myKycSessions.first().getNFTImages()
//                sdk.myKycSessions.first().requestMinting(images.first().id, 3u)
//            }
//        }
//        binding.mintNFT.setOnClickListener {
//            lifecycleScope.launch {
//                val url = sdk.myKycSessions.first().mint()
//                println("probably done : $url")
//            }
//        }
//        binding.resendEmail.setOnClickListener {
//            lifecycleScope.launch {
//                sdk.myKycSessions.first().resendConfirmationEmail()
//            }
//        }
//    }

}