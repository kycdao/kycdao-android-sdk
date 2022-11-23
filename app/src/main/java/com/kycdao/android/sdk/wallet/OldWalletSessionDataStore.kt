package com.kycdao.android.sdk.wallet

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.walletSessionKeyDataStore: DataStore<Preferences> by preferencesDataStore(name = "old_wallet_session_key")

suspend fun DataStore<Preferences>.saveWCKey(key: String){
	val oldKey= stringPreferencesKey("old_connection_key")
	edit{ settings->
		settings[oldKey] = key
	}
}
suspend fun DataStore<Preferences>.getOldWCKey(): String?{
	val oldKey= stringPreferencesKey("old_connection_key")
	val preferences =data.first()
	return preferences[oldKey]
}
suspend fun DataStore<Preferences>.deleteOldKey() {
	val oldKey= stringPreferencesKey("old_connection_key")
	edit {
		it.remove(oldKey)
	}
}