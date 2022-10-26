//[app](../../../index.md)/[com.kycdao.android.sdk.network](../index.md)/[SessionCookieJar](index.md)

# SessionCookieJar

[androidJvm]\
class [SessionCookieJar](index.md)(sharedPreferences: [SharedPreferences](https://developer.android.com/reference/kotlin/android/content/SharedPreferences.html)) : CookieJar

## Constructors

| | |
|---|---|
| [SessionCookieJar](-session-cookie-jar.md) | [androidJvm]<br>fun [SessionCookieJar](-session-cookie-jar.md)(sharedPreferences: [SharedPreferences](https://developer.android.com/reference/kotlin/android/content/SharedPreferences.html)) |

## Functions

| Name | Summary |
|---|---|
| [loadForRequest](load-for-request.md) | [androidJvm]<br>open override fun [loadForRequest](load-for-request.md)(url: HttpUrl): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;Cookie&gt; |
| [saveFromResponse](save-from-response.md) | [androidJvm]<br>open override fun [saveFromResponse](save-from-response.md)(url: HttpUrl, cookies: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;Cookie&gt;) |
