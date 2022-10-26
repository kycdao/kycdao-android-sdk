//[app](../../index.md)/[com.kycdao.android.sdk.ui](index.md)

# Package com.kycdao.android.sdk.ui

## Types

| Name | Summary |
|---|---|
| [FragmentFactory](-fragment-factory/index.md) | [androidJvm]<br>interface [FragmentFactory](-fragment-factory/index.md) : [Serializable](https://developer.android.com/reference/kotlin/java/io/Serializable.html) |
| [GlideWithSession](-glide-with-session/index.md) | [androidJvm]<br>class [GlideWithSession](-glide-with-session/index.md) |
| [KycActivityResultContract](-kyc-activity-result-contract/index.md) | [androidJvm]<br>abstract class [KycActivityResultContract](-kyc-activity-result-contract/index.md)&lt;[Input](-kyc-activity-result-contract/index.md), [Result](-kyc-activity-result-contract/index.md) : [Serializable](https://developer.android.com/reference/kotlin/java/io/Serializable.html)&gt; : [ActivityResultContract](https://developer.android.com/reference/kotlin/androidx/activity/result/contract/ActivityResultContract.html)&lt;[Input](-kyc-activity-result-contract/index.md), [Result](-kyc-activity-result-contract/index.md)?&gt; |
| [ProgressActivity](-progress-activity/index.md) | [androidJvm]<br>class [ProgressActivity](-progress-activity/index.md) : [AppCompatActivity](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AppCompatActivity.html), AndroidScopeComponent |
| [SdkActivity](-sdk-activity/index.md) | [androidJvm]<br>class [SdkActivity](-sdk-activity/index.md) : [AppCompatActivity](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AppCompatActivity.html) |
| [ToggleableAppBarLayoutBehavior](-toggleable-app-bar-layout-behavior/index.md) | [androidJvm]<br>class [ToggleableAppBarLayoutBehavior](-toggleable-app-bar-layout-behavior/index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), attrs: [AttributeSet](https://developer.android.com/reference/kotlin/android/util/AttributeSet.html)? = null) : AppBarLayout.Behavior |

## Functions

| Name | Summary |
|---|---|
| [convertBigInteger](convert-big-integer.md) | [androidJvm]<br>fun [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html).[convertBigInteger](convert-big-integer.md)(): [BigInteger](https://developer.android.com/reference/kotlin/java/math/BigInteger.html) |
| [enableScrolling](enable-scrolling.md) | [androidJvm]<br>fun AppBarLayout.[enableScrolling](enable-scrolling.md)(enable: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [hideKeyboard](hide-keyboard.md) | [androidJvm]<br>fun [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html).[hideKeyboard](hide-keyboard.md)()<br>fun [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html).[hideKeyboard](hide-keyboard.md)()<br>fun [Context](https://developer.android.com/reference/kotlin/android/content/Context.html).[hideKeyboard](hide-keyboard.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)) |
| [loadSvgWithCookie](load-svg-with-cookie.md) | [androidJvm]<br>fun [ImageView](https://developer.android.com/reference/kotlin/android/widget/ImageView.html).[loadSvgWithCookie](load-svg-with-cookie.md)(url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), cookieJar: CookieJar) |
| [setButtonEnabler](set-button-enabler.md) | [androidJvm]<br>fun [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html).[setButtonEnabler](set-button-enabler.md)(button: [Button](https://developer.android.com/reference/kotlin/android/widget/Button.html), livedata: [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;) |
| [setDrawable](set-drawable.md) | [androidJvm]<br>fun [ImageView](https://developer.android.com/reference/kotlin/android/widget/ImageView.html).[setDrawable](set-drawable.md)(resId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?) |
| [setHeaderShadow](set-header-shadow.md) | [androidJvm]<br>fun [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html).[setHeaderShadow](set-header-shadow.md)(shadow: [View](https://developer.android.com/reference/kotlin/android/view/View.html), appBarLayout: AppBarLayout) |
| [setupAndShowOnCondition](setup-and-show-on-condition.md) | [androidJvm]<br>fun &lt;[T](setup-and-show-on-condition.md) : [View](https://developer.android.com/reference/kotlin/android/view/View.html)&gt; [T](setup-and-show-on-condition.md).[setupAndShowOnCondition](setup-and-show-on-condition.md)(condition: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), setup: (view: [T](setup-and-show-on-condition.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [uiModule](ui-module.md) | [androidJvm]<br>val [uiModule](ui-module.md): Module |
