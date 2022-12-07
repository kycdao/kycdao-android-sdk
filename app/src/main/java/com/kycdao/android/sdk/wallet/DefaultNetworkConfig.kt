package com.kycdao.android.sdk.wallet

import com.kycdao.android.sdk.model.NetworkOption

internal val defaultNetworkConfigs : List<NetworkOption> = listOf(
	NetworkOption("eip155:42220", "https://forno.celo.org"), //Celo Mainnet
	NetworkOption("eip155:44787", "https://alfajores-forno.celo-testnet.org"), //Celo Alfajores
	NetworkOption("eip155:89", "https://polygon-rpc.com"), //Polygon Mainnet
	NetworkOption("eip155:80001", "https://rpc-mumbai.maticvigil.com/"), //Polygon Mumbai
)