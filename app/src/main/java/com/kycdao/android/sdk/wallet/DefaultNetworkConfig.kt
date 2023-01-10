package com.kycdao.android.sdk.wallet

import com.kycdao.android.sdk.model.NetworkConfiguration

internal val defaultNetworkConfigs : List<NetworkConfiguration> = listOf(
	NetworkConfiguration("eip155:42220", "https://forno.celo.org"), //Celo Mainnet
	NetworkConfiguration("eip155:44787", "https://alfajores-forno.celo-testnet.org"), //Celo Alfajores
	NetworkConfiguration("eip155:89", "https://polygon-rpc.com"), //Polygon Mainnet
	NetworkConfiguration("eip155:80001", "https://rpc-mumbai.maticvigil.com/"), //Polygon Mumbai

)