package com.kycdao.android.sdk.usecase

import androidx.activity.result.ActivityResultRegistry
import com.kycdao.android.sdk.db.LocalDataSource
import com.kycdao.android.sdk.model.KycUser
import com.kycdao.android.sdk.model.PersonalDataResult
import com.kycdao.android.sdk.ui.KycActivityResultContract
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GetUserInformationUseCaseImp(
    private val activityResultRegistry: ActivityResultRegistry,
    private val localDataSource: LocalDataSource,
    private val getUserInformationContract : KycActivityResultContract<Any?, PersonalDataResult>
    ) : GetUserInformationUseCase {

    override suspend fun invoke(kycUser: KycUser) : Boolean = suspendCoroutine { continuation ->
        val launcher = activityResultRegistry.register(
            "GetUserInformationContract",
            getUserInformationContract
        ) {
            it?.let {
                Timber.d( "user information entered: $it")
                localDataSource.saveKycUser(kycUser.copy(
                    email = it.email,
                    residency = it.residency,
                    isLegalEntity = it.isLegalEntity
                ))
                continuation.resume(true)
            } ?: run {
                Timber.d( "user information screen canceled")
                continuation.resume(false)
            }
        }
        Timber.d( "open user information screen")
        launcher.launch(Unit)
    }

}