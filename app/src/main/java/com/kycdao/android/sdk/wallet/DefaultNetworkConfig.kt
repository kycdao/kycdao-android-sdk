package com.kycdao.android.sdk.wallet

import com.kycdao.android.sdk.model.NetworkConfig

internal val defaultNetworkConfigs : List<NetworkConfig> = listOf(
	NetworkConfig("eip155:42220", "https://forno.celo.org"), //Celo Mainnet
	NetworkConfig("eip155:44787", "https://alfajores-forno.celo-testnet.org"), //Celo Alfajores
	NetworkConfig("eip155:89", "https://polygon-rpc.com"), //Polygon Mainnet
	NetworkConfig("eip155:80001", "https://rpc-mumbai.maticvigil.com/"), //Polygon Mumbai

)