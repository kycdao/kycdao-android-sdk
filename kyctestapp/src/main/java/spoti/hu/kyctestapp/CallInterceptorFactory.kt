package spoti.hu.kyctestapp

import okhttp3.Interceptor
import spoti.hu.kyctestapp.manager.SDKManager

class CallInterceptorFactory(
    private val sdkManager: SDKManager
) {

    fun createHeaderChangingInterceptor(): Interceptor {
        return Interceptor { chain ->
            val newRequest = chain.request().newBuilder()

            val sdkManager = sdkManager.getVerificationSession()
            newRequest.header("session_id", sdkManager.id)

            chain.proceed(newRequest.build() ?: chain.request())

        }
    }
}