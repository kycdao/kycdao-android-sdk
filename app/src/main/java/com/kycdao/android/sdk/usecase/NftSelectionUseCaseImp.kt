package com.kycdao.android.sdk.usecase

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultRegistry
import com.kycdao.android.sdk.db.LocalDataSource
import com.kycdao.android.sdk.model.AvailableImage
import com.kycdao.android.sdk.model.KycSession
import com.kycdao.android.sdk.ui.KycActivityResultContract
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NftSelectionUseCaseImp(
    private val nftSelectorContract: KycActivityResultContract<List<AvailableImage>, String>
) : NftSelectionUseCase {

    override suspend fun invoke(kycSession: KycSession, activity: ComponentActivity) : String? = suspendCoroutine { continuation ->
        val list =
            kycSession.sessionData.user.availableImages.filter { it.imageType == "Identicon" }

        Timber.d( "---------- Input ----------")
        Timber.d( "image list: $list")

        val launcher = activity.activityResultRegistry.register(
            "GetUserInformationContract",
            nftSelectorContract
        ) {
            Timber.d( "---------- Output ----------")
            Timber.d( "selected image: $it")
            continuation.resume(it)
        }
        launcher.launch(list)
    }
}