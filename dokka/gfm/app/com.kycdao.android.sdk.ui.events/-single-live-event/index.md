//[app](../../../index.md)/[com.kycdao.android.sdk.ui.events](../index.md)/[SingleLiveEvent](index.md)

# SingleLiveEvent

[androidJvm]\
class [SingleLiveEvent](index.md)&lt;[T](index.md)&gt; : [MutableLiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/MutableLiveData.html)&lt;[T](index.md)&gt;

## Constructors

| | |
|---|---|
| [SingleLiveEvent](-single-live-event.md) | [androidJvm]<br>fun [SingleLiveEvent](-single-live-event.md)() |

## Functions

| Name | Summary |
|---|---|
| [call](call.md) | [androidJvm]<br>@[MainThread](https://developer.android.com/reference/kotlin/androidx/annotation/MainThread.html)<br>fun [call](call.md)()<br>Used for cases where T is Void, to make calls cleaner. |
| [getValue](index.md#685674515%2FFunctions%2F-912451524) | [androidJvm]<br>@[Nullable](https://developer.android.com/reference/kotlin/androidx/annotation/Nullable.html)<br>open fun [getValue](index.md#685674515%2FFunctions%2F-912451524)(): [T](index.md)? |
| [hasActiveObservers](index.md#-1328333103%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [hasActiveObservers](index.md#-1328333103%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hasObservers](index.md#-1046544021%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [hasObservers](index.md#-1046544021%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [observe](observe.md) | [androidJvm]<br>@[MainThread](https://developer.android.com/reference/kotlin/androidx/annotation/MainThread.html)<br>open override fun [observe](observe.md)(owner: [LifecycleOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleOwner.html), observer: [Observer](https://developer.android.com/reference/kotlin/androidx/lifecycle/Observer.html)&lt;in [T](index.md)&gt;) |
| [observeForever](index.md#-1123335282%2FFunctions%2F-912451524) | [androidJvm]<br>@[MainThread](https://developer.android.com/reference/kotlin/androidx/annotation/MainThread.html)<br>open fun [observeForever](index.md#-1123335282%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [Observer](https://developer.android.com/reference/kotlin/androidx/lifecycle/Observer.html)&lt;in [T](index.md)&gt;) |
| [postValue](index.md#1536303861%2FFunctions%2F-912451524) | [androidJvm]<br>open override fun [postValue](index.md#1536303861%2FFunctions%2F-912451524)(p0: [T](index.md)) |
| [removeObserver](index.md#758495263%2FFunctions%2F-912451524) | [androidJvm]<br>@[MainThread](https://developer.android.com/reference/kotlin/androidx/annotation/MainThread.html)<br>open fun [removeObserver](index.md#758495263%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [Observer](https://developer.android.com/reference/kotlin/androidx/lifecycle/Observer.html)&lt;in [T](index.md)&gt;) |
| [removeObservers](index.md#1487287389%2FFunctions%2F-912451524) | [androidJvm]<br>@[MainThread](https://developer.android.com/reference/kotlin/androidx/annotation/MainThread.html)<br>open fun [removeObservers](index.md#1487287389%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [LifecycleOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleOwner.html)) |
| [setValue](set-value.md) | [androidJvm]<br>@[MainThread](https://developer.android.com/reference/kotlin/androidx/annotation/MainThread.html)<br>open override fun [setValue](set-value.md)(t: [T](index.md)?) |
