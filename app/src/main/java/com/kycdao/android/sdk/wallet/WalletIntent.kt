package com.kycdao.android.sdk.wallet

import android.content.Context
import android.content.Intent
import android.net.Uri
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject
import timber.log.Timber

object WalletIntent: KoinComponent{
    private val context: Context by inject()

    fun executeFromUri(wcUri: String){
        Timber.d( "---------- Input ----------")
        Timber.d( "uri: $wcUri")

        val openIntent = Intent(Intent.ACTION_VIEW)
        openIntent.data = Uri.parse(wcUri)
        openIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        Timber.d( "wallet connect intent: $openIntent")

        val chooser = Intent.createChooser(openIntent, "") // TODO title
        chooser.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        Timber.d( "start wallet connect intent")
        context.startActivity(chooser)
    }
}