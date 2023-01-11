package com.kycdao.android.sdk.verificationSession


enum class KycDaoEnvironment(val serverURL : String, val demoMode: Boolean, val rnText: String) {
	Production("https://kycdao.xyz", false, "PRODUCTION"),
	Development("https://staging.kycdao.xyz", true, "DEV")
}