package com.kycdao.android.sdk

import org.koin.dsl.module
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService

val web3Module = module {

    single<Web3j> {
        Web3j.build(HttpService("https://polygon-mumbai.infura.io/v3/8edae24121f74398b57da7ff5a3729a4"))
    }

}