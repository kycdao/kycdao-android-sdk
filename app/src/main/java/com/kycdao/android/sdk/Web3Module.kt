package com.kycdao.android.sdk

import org.koin.dsl.module
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService

val web3Module = module {

    factory<Web3j> { params->
        Web3j.build(HttpService(params.get<String>()))
    }

}