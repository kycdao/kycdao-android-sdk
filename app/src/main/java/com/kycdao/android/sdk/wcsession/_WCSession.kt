package com.kycdao.android.sdk.wcsession

import org.walletconnect.Session
import timber.log.Timber

typealias Function = () -> Unit

class _WCSession(
    var config: Session.Config,
    var session: Session
) {
    var approvedCallback : Function? = null

    init {
        session.addCallback(object : Session.Callback {
            override fun onStatus(status: Session.Status) {
                Timber.d("onStatus: $status")
                if (status == Session.Status.Approved) {
                    approvedCallback?.invoke()
                }
            }

            override fun onMethodCall(call: Session.MethodCall) {
            }
        })
    }

}