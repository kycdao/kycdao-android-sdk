//[app](../../index.md)/[com.kycdao.android.sdk.ui.events](index.md)

# Package com.kycdao.android.sdk.ui.events

## Types

| Name | Summary |
|---|---|
| [MakeToastEvent](-make-toast-event/index.md) | [androidJvm]<br>data class [MakeToastEvent](-make-toast-event/index.md)(val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [ViewModelEvent](-view-model-event/index.md) |
| [SingleLiveEvent](-single-live-event/index.md) | [androidJvm]<br>class [SingleLiveEvent](-single-live-event/index.md)&lt;[T](-single-live-event/index.md)&gt; : [MutableLiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/MutableLiveData.html)&lt;[T](-single-live-event/index.md)&gt; |
| [ViewModelEvent](-view-model-event/index.md) | [androidJvm]<br>interface [ViewModelEvent](-view-model-event/index.md) |

## Functions

| Name | Summary |
|---|---|
| [asSingleEvent](as-single-event.md) | [androidJvm]<br>fun &lt;[T](as-single-event.md)&gt; Flow&lt;[T](as-single-event.md)&gt;.[asSingleEvent](as-single-event.md)(scope: CoroutineScope): [SingleLiveEvent](-single-live-event/index.md)&lt;[T](as-single-event.md)&gt; |
