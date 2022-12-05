package com.kycdao.android.sdk.model

import android.net.Uri

data class MintingResult(
	val explorerURL: Uri?,
	val transactionId: String,
	val tokenId: String
	)