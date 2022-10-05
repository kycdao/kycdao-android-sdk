package com.kycdao.android.sdk.network

import android.content.SharedPreferences
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class SessionCookieJar(
    private val sharedPreferences: SharedPreferences
) : CookieJar {

    private lateinit var cookies: List<String>

    init {
        load()
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        this.cookies = cookies.map { it.toString() }
        save()
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookies.map { Cookie.Companion.parse(url, it)!! }
    }

    private fun load() {
        cookies = sharedPreferences.getStringSet("cookies", emptySet())?.toList() ?: emptyList()
    }

    private fun save() {
        sharedPreferences.edit().putStringSet("cookies", this.cookies.toSet()).apply()
    }

}