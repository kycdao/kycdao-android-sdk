package com.kycdao.android.sdk.verificationSession


enum class KycDaoEnvironment(val serverURL : String, val demoMode: Boolean) {
	Production("https://kycdao.xyz", false),
	Development("https://staging.kycdao.xyz", true)
}