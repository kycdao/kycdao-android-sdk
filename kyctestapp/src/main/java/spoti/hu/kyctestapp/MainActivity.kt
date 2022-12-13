package spoti.hu.kyctestapp

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kycdao.android.sdk.verificationSession.VerificationManager
import com.kycdao.android.sdk.verificationSession.VerificationSession
import com.kycdao.android.sdk.model.PersonalData
import com.kycdao.android.sdk.model.VerificationType
import com.kycdao.android.sdk.util.Resource
import com.kycdao.android.sdk.util.toText
import com.kycdao.android.sdk.verificationSession.KycDaoEnvironment
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
	var myKycSessions: MutableList<VerificationSession> = mutableListOf()


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		Timber.plant(Timber.DebugTree());
		Timber.d("CREATE")
		VerificationManager.configure(
			VerificationManager.Configuration(KycDaoEnvironment.Development)
		)
		lifecycleScope.launchWhenCreated {
			myWalletSession.collect{
				binding.connectWallet.text = if(it!= null){
					"wallet connected"
				}else{
					"connect"
				}
			}
		}
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
		}
		binding.acceptDisclaimer.setOnClickListener {
			lifecycleScope.launch {
				myKycSessions.first().acceptDisclaimer()
			}
		}
		binding.getPerYear.setOnClickListener {
			lifecycleScope.launch{
				val res = myKycSessions.first().getMembershipCostPerYear()
				Timber.d("$res $")
			}
		}
		binding.estimateCost.setOnClickListener {
			lifecycleScope.launch{
				val res = myKycSessions.first().estimatePayment(3u)
				Timber.d(res.paymentAmountText)
			}
		}
		binding.hasTokenValid.setOnClickListener {
			val walletAddress = myWalletSession.value?.getAvailableWallets()?.first()
				?: throw Exception("No wallet found")
			lifecycleScope.launch {
				binding.hasTokenValid.text = VerificationManager.hasValidToken(
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
				myKycSessions.add(VerificationManager.createSession(walletAddress, myWalletSession.value!!))
			}
		}
		binding.login.setOnClickListener {
			lifecycleScope.launch {
				myKycSessions.first().login()
				toast("UserLoggedIn: ${myKycSessions.first().loggedIn}")
			}
		}
		binding.addPersonalInfoBtn.setOnClickListener {
			val mockPersonalInfo = PersonalData(
				email = "adamszucs2000@gmail.com",
				residency = "HUN",
			)
			lifecycleScope.launch {
				myKycSessions.first().setPersonalData(mockPersonalInfo)
				myKycSessions.first().resumeOnEmailConfirmed()
				toast("Email confirmed")
			}
		}
		binding.startPersona.setOnClickListener {
			lifecycleScope.launch {
				val res = myKycSessions.first().startIdentification(this@MainActivity)
				toast("Persona result: ${res.name}")
			}
		}
		binding.selectNFT.setOnClickListener {
			lifecycleScope.launch {
				val images = myKycSessions.first().getNFTImages()
				myKycSessions.first().requestMinting(images.first().id,3u)
				toast("Mint authorized")
			}
		}
		binding.mintNFT.setOnClickListener {
			lifecycleScope.launch {
				val url = myKycSessions.first().mint()
				println("probably done : $url")
				toast("Done")
			}
		}
		binding.resendEmail.setOnClickListener {
			lifecycleScope.launch{
				myKycSessions.first().resendConfirmationEmail()
				toast("Email resent")
			}
		}

	}
}

fun Activity.toast(message: String){
	Toast.makeText(this, message,
		Toast.LENGTH_SHORT).show();
}