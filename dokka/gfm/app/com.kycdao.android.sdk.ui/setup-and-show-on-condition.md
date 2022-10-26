//[app](../../index.md)/[com.kycdao.android.sdk.ui](index.md)/[setupAndShowOnCondition](setup-and-show-on-condition.md)

# setupAndShowOnCondition

[androidJvm]\
fun &lt;[T](setup-and-show-on-condition.md) : [View](https://developer.android.com/reference/kotlin/android/view/View.html)&gt; [T](setup-and-show-on-condition.md).[setupAndShowOnCondition](setup-and-show-on-condition.md)(condition: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), setup: (view: [T](setup-and-show-on-condition.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

- 
   The [setup](setup-and-show-on-condition.md) function will only run, if the [condition](setup-and-show-on-condition.md) is true.
- 
   If the [condition](setup-and-show-on-condition.md) is false, the visibility will be [View.GONE](https://developer.android.com/reference/kotlin/android/view/View.html#gone), otherwise the visibility will be [View.VISIBLE](https://developer.android.com/reference/kotlin/android/view/View.html#visible)
