package com.kycdao.android.sdk.usecase

import android.content.Context
import android.content.Intent
import android.net.Uri
import timber.log.Timber


class WalletIntentImp(
    private val context: Context
) : WalletIntent {

    override fun invoke(wcUri: String) {
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

//    override fun invoke(wcSession: Session.Config) {
//
//        val openIntent = Intent(Intent.ACTION_VIEW)
//        openIntent.data = Uri.parse(wcSession.toWCUri())
//
//        val broadcastIntent = Intent("ACTION_TEST")
//        val pendingIntent = PendingIntent.getBroadcast(context, 0, broadcastIntent, PendingIntent.FLAG_MUTABLE)
//
//        val chooser = Intent.createChooser(openIntent, "", pendingIntent.intentSender) // TODO title
//        chooser.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//
//        context.startActivity(chooser)
//
//        context.registerReceiver(object : BroadcastReceiver() {
//            override fun onReceive(context: Context?, intent: Intent?) {
//                val selectedAppPackage = intent?.extras?.get(Intent.EXTRA_CHOSEN_COMPONENT)
//                Log.d("ACTION_TEST", "selectedAppPackage: $selectedAppPackage")
//            }
//        }, IntentFilter("ACTION_TEST"))
//    }


}