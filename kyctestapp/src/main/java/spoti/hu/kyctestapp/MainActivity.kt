package spoti.hu.kyctestapp

import android.R.attr.bitmap
import android.graphics.Color
import android.os.Bundle
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kycdao.android.sdk.kycSession.KycManager
import com.kycdao.android.sdk.kycSession.KycSession
import com.kycdao.android.sdk.model.PersonalData
import com.kycdao.android.sdk.model.VerificationType
import com.kycdao.android.sdk.util.Resource
import com.kycdao.android.sdk.wallet.WalletConnectManager
import com.kycdao.android.sdk.wallet.WalletConnectSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import spoti.hu.kyctestapp.databinding.ActivityMainBinding
import timber.log.Timber


class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	var myWalletSession= MutableStateFlow<WalletConnectSession?>(null)
	var myKycSessions: MutableList<KycSession> = mutableListOf()


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		Timber.plant(Timber.DebugTree());
		Timber.d("CREATE")
		lifecycleScope.launchWhenCreated {
			myWalletSession.collect{
				binding.connectWallet.text = if(it!= null){
					"wallet connected"
				}else{
					"connect"
				}
			}
		}
		WalletConnectManager.addCustomRPCUrl("eip155:80001", "https://polygon-mumbai.infura.io/v3/8edae24121f74398b57da7ff5a3729a4")
		lifecycleScope.launchWhenCreated {
			WalletConnectManager.wcURI.collect{
				// Initializing the QR Encoder with your value to be encoded, type you required and Dimension
				// Initializing the QR Encoder with your value to be encoded, type you required and Dimension
				val qrgEncoder =
					QRGEncoder(it, null, QRGContents.Type.TEXT, 400)
				qrgEncoder.colorBlack = Color.WHITE
				qrgEncoder.colorWhite = Color.BLACK
				try {
					// Getting QR-Code as Bitmap
					val bitmap = qrgEncoder.bitmap
					// Setting Bitmap to ImageView
					binding.qrImageView.setImageBitmap(bitmap)
				} catch (e: Exception) {

				}
			}
		}
		WalletConnectManager.startListening()
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		lifecycleScope.launchWhenCreated {
			WalletConnectManager.sessionsState.collect { result ->
				when(result){
					is Resource.Failure -> {
						Timber.d("FAIL wc")
						//WalletConnectManager.startListening()
					}
					is Resource.Success -> {
						Timber.d("SUCCESS wc")
						myWalletSession.update { result.data }
					}
				}

			}
		}/* { walletSession ->
			println("connectionEstablished ${walletSession.getAvailableWallets()?.first()}" )
			myWalletSession.update { walletSession }
			println("Acoounts: "+walletSession.accounts)
			println("Icons: "+walletSession.icons)
			println("Name: "+walletSession.name)
		}*/
		binding.acceptDisclaimer.setOnClickListener {
			lifecycleScope.launch {
				myKycSessions.first().acceptDisclaimer()
			}
		}
		binding.hasTokenValid.setOnClickListener {
			val walletAddress = myWalletSession.value?.getAvailableWallets()?.first()
				?: throw Exception("No wallet found")
			lifecycleScope.launch {
				binding.hasTokenValid.text = KycManager.hasValidToken(
					VerificationType.KYC,
					walletAddress,
					myWalletSession.value!!
				).toString()
			}
		}
		binding.connectWallet.setOnClickListener {
			WalletConnectManager.connectWallet()
		}
		binding.createKyc.setOnClickListener {
			val walletAddress = myWalletSession.value?.getAvailableWallets()?.first()
				?: throw Exception("No wallet found")
			lifecycleScope.launch {
				myKycSessions.add(KycManager.createSession(walletAddress, myWalletSession.value!!))
			}
		}
		binding.login.setOnClickListener {
			lifecycleScope.launch {
				myKycSessions.first().login()
				println("userID: ${myKycSessions.first().loggedIn}")
			}
		}
		binding.addPersonalInfoBtn.setOnClickListener {
			val mockPersonalInfo = PersonalData(
				email = "adamszucs2000@gmail.com",
				residency = "HUN",
				isLegalEntity = false
			)
			lifecycleScope.launch {
				myKycSessions.first().setPersonalData(mockPersonalInfo)
				myKycSessions.first().resumeOnEmailConfirmed()
			}
		}
		binding.startPersona.setOnClickListener {
			lifecycleScope.launch {
				myKycSessions.first().startIdentification(this@MainActivity)
			}
		}
		binding.selectNFT.setOnClickListener {
			lifecycleScope.launch {
				val images = myKycSessions.first().getNFTImages()
				myKycSessions.first().requestMinting(images.first().id)
			}
		}
		binding.mintNFT.setOnClickListener {
			lifecycleScope.launch {
				val url = myKycSessions.first().mint()
				println("probably done : $url")
			}
		}

	}
}