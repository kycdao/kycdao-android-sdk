//[app](../../../index.md)/[com.kycdao.android.sdk.ui](../index.md)/[ToggleableAppBarLayoutBehavior](index.md)

# ToggleableAppBarLayoutBehavior

[androidJvm]\
class [ToggleableAppBarLayoutBehavior](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), attrs: [AttributeSet](https://developer.android.com/reference/kotlin/android/util/AttributeSet.html)? = null) : AppBarLayout.Behavior

## Constructors

| | |
|---|---|
| [ToggleableAppBarLayoutBehavior](-toggleable-app-bar-layout-behavior.md) | [androidJvm]<br>fun [ToggleableAppBarLayoutBehavior](-toggleable-app-bar-layout-behavior.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), attrs: [AttributeSet](https://developer.android.com/reference/kotlin/android/util/AttributeSet.html)? = null) |

## Functions

| Name | Summary |
|---|---|
| [blocksInteractionBelow](index.md#421717315%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [blocksInteractionBelow](index.md#421717315%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [enableScroll](enable-scroll.md) | [androidJvm]<br>fun [enableScroll](enable-scroll.md)(enable: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [getInsetDodgeRect](index.md#1805744031%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [getInsetDodgeRect](index.md#1805744031%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout, @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p2: [Rect](https://developer.android.com/reference/kotlin/android/graphics/Rect.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getLeftAndRightOffset](index.md#-1038397296%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [getLeftAndRightOffset](index.md#-1038397296%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getScrimColor](index.md#89773827%2FFunctions%2F-912451524) | [androidJvm]<br>@[ColorInt](https://developer.android.com/reference/kotlin/androidx/annotation/ColorInt.html)<br>open fun [getScrimColor](index.md#89773827%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getScrimOpacity](index.md#719597931%2FFunctions%2F-912451524) | [androidJvm]<br>@[FloatRange](https://developer.android.com/reference/kotlin/androidx/annotation/FloatRange.html)(from = 0.0, to = 1.0)<br>open fun [getScrimOpacity](index.md#719597931%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout): [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) |
| [getTopAndBottomOffset](index.md#-1380464881%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [getTopAndBottomOffset](index.md#-1380464881%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isHorizontalOffsetEnabled](index.md#364832613%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [isHorizontalOffsetEnabled](index.md#364832613%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isScrollingAllowed](is-scrolling-allowed.md) | [androidJvm]<br>fun [isScrollingAllowed](is-scrolling-allowed.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isVerticalOffsetEnabled](index.md#950075831%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [isVerticalOffsetEnabled](index.md#950075831%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [layoutDependsOn](index.md#-2140965216%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [layoutDependsOn](index.md#-2140965216%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout, @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p2: [View](https://developer.android.com/reference/kotlin/android/view/View.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onApplyWindowInsets](index.md#-1227673471%2FFunctions%2F-912451524) | [androidJvm]<br>@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)<br>open fun [onApplyWindowInsets](index.md#-1227673471%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout, @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p2: [WindowInsetsCompat](https://developer.android.com/reference/kotlin/androidx/core/view/WindowInsetsCompat.html)): [WindowInsetsCompat](https://developer.android.com/reference/kotlin/androidx/core/view/WindowInsetsCompat.html) |
| [onAttachedToLayoutParams](index.md#-245595421%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onAttachedToLayoutParams](index.md#-245595421%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout.LayoutParams](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.LayoutParams.html)) |
| [onDependentViewChanged](index.md#-269516479%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onDependentViewChanged](index.md#-269516479%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout, @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p2: [View](https://developer.android.com/reference/kotlin/android/view/View.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onDependentViewRemoved](index.md#-1890838035%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onDependentViewRemoved](index.md#-1890838035%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout, @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p2: [View](https://developer.android.com/reference/kotlin/android/view/View.html)) |
| [onDetachedFromLayoutParams](index.md#-2040678149%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onDetachedFromLayoutParams](index.md#-2040678149%2FFunctions%2F-912451524)() |
| [onInterceptTouchEvent](index.md#1416600947%2FFunctions%2F-912451524) | [androidJvm]<br>open override fun [onInterceptTouchEvent](index.md#1416600947%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout, @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p2: [MotionEvent](https://developer.android.com/reference/kotlin/android/view/MotionEvent.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onLayoutChild](index.md#-1119629286%2FFunctions%2F-912451524) | [androidJvm]<br>open override fun [onLayoutChild](index.md#-1119629286%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout, p2: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onMeasureChild](index.md#1629652635%2FFunctions%2F-912451524) | [androidJvm]<br>open override fun [onMeasureChild](index.md#1629652635%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout, p2: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p3: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p4: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p5: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onNestedFling](index.md#1755334106%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onNestedFling](index.md#1755334106%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout, @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p2: [View](https://developer.android.com/reference/kotlin/android/view/View.html), p3: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html), p4: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html), p5: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onNestedPreFling](index.md#1721382721%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onNestedPreFling](index.md#1721382721%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout, @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p2: [View](https://developer.android.com/reference/kotlin/android/view/View.html), p3: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html), p4: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onNestedPreScroll](index.md#39216554%2FFunctions%2F-912451524) | [androidJvm]<br>~~open~~ ~~fun~~ [~~onNestedPreScroll~~](index.md#39216554%2FFunctions%2F-912451524)~~(~~@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout, @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p2: [View](https://developer.android.com/reference/kotlin/android/view/View.html), p3: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p4: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p5: [IntArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html)~~)~~<br>open override fun [onNestedPreScroll](index.md#-1909062253%2FFunctions%2F-912451524)(p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout, p2: [View](https://developer.android.com/reference/kotlin/android/view/View.html), p3: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p4: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p5: [IntArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html), p6: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [onNestedScroll](index.md#358384469%2FFunctions%2F-912451524) | [androidJvm]<br>~~open~~ ~~fun~~ [~~onNestedScroll~~](index.md#358384469%2FFunctions%2F-912451524)~~(~~@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout, @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p2: [View](https://developer.android.com/reference/kotlin/android/view/View.html), p3: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p4: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p5: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p6: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)~~)~~<br>~~open~~ ~~fun~~ [~~onNestedScroll~~](index.md#316416744%2FFunctions%2F-912451524)~~(~~@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout, @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p2: [View](https://developer.android.com/reference/kotlin/android/view/View.html), p3: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p4: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p5: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p6: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p7: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)~~)~~<br>open override fun [onNestedScroll](index.md#45306548%2FFunctions%2F-912451524)(p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout, p2: [View](https://developer.android.com/reference/kotlin/android/view/View.html), p3: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p4: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p5: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p6: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p7: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p8: [IntArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html)) |
| [onNestedScrollAccepted](index.md#1619341037%2FFunctions%2F-912451524) | [androidJvm]<br>~~open~~ ~~fun~~ [~~onNestedScrollAccepted~~](index.md#1619341037%2FFunctions%2F-912451524)~~(~~@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout, @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p2: [View](https://developer.android.com/reference/kotlin/android/view/View.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p3: [View](https://developer.android.com/reference/kotlin/android/view/View.html), p4: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)~~)~~<br>open fun [onNestedScrollAccepted](index.md#-106641328%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout, @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p2: [View](https://developer.android.com/reference/kotlin/android/view/View.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p3: [View](https://developer.android.com/reference/kotlin/android/view/View.html), p4: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p5: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [onRequestChildRectangleOnScreen](index.md#711332843%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onRequestChildRectangleOnScreen](index.md#711332843%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout, @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p2: [Rect](https://developer.android.com/reference/kotlin/android/graphics/Rect.html), p3: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onRestoreInstanceState](index.md#1451061856%2FFunctions%2F-912451524) | [androidJvm]<br>open override fun [onRestoreInstanceState](index.md#1451061856%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout, p2: [Parcelable](https://developer.android.com/reference/kotlin/android/os/Parcelable.html)) |
| [onSaveInstanceState](index.md#-940215600%2FFunctions%2F-912451524) | [androidJvm]<br>open override fun [onSaveInstanceState](index.md#-940215600%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout): [Parcelable](https://developer.android.com/reference/kotlin/android/os/Parcelable.html)? |
| [onStartNestedScroll](index.md#-472325442%2FFunctions%2F-912451524) | [androidJvm]<br>~~open~~ ~~fun~~ [~~onStartNestedScroll~~](index.md#-472325442%2FFunctions%2F-912451524)~~(~~@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout, @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p2: [View](https://developer.android.com/reference/kotlin/android/view/View.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p3: [View](https://developer.android.com/reference/kotlin/android/view/View.html), p4: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)~~)~~~~:~~ [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>open override fun [onStartNestedScroll](on-start-nested-scroll.md)(parent: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), child: AppBarLayout, directTargetChild: [View](https://developer.android.com/reference/kotlin/android/view/View.html), target: [View](https://developer.android.com/reference/kotlin/android/view/View.html), nestedScrollAxes: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), type: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onStopNestedScroll](index.md#-282191433%2FFunctions%2F-912451524) | [androidJvm]<br>~~open~~ ~~fun~~ [~~onStopNestedScroll~~](index.md#-282191433%2FFunctions%2F-912451524)~~(~~@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout, @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p2: [View](https://developer.android.com/reference/kotlin/android/view/View.html)~~)~~<br>open override fun [onStopNestedScroll](index.md#-954766554%2FFunctions%2F-912451524)(p0: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), @[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p1: AppBarLayout, p2: [View](https://developer.android.com/reference/kotlin/android/view/View.html), p3: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [onTouchEvent](on-touch-event.md) | [androidJvm]<br>open override fun [onTouchEvent](on-touch-event.md)(parent: [CoordinatorLayout](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/CoordinatorLayout.html), child: AppBarLayout, ev: [MotionEvent](https://developer.android.com/reference/kotlin/android/view/MotionEvent.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [setDragCallback](index.md#-1337921525%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [setDragCallback](index.md#-1337921525%2FFunctions%2F-912451524)(@[Nullable](https://developer.android.com/reference/kotlin/androidx/annotation/Nullable.html)p0: AppBarLayout.BaseBehavior.BaseDragCallback&lt;AppBarLayout&gt;?) |
| [setHorizontalOffsetEnabled](index.md#1309113106%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [setHorizontalOffsetEnabled](index.md#1309113106%2FFunctions%2F-912451524)(p0: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [setLeftAndRightOffset](index.md#579713186%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [setLeftAndRightOffset](index.md#579713186%2FFunctions%2F-912451524)(p0: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [setTopAndBottomOffset](index.md#-2096931743%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [setTopAndBottomOffset](index.md#-2096931743%2FFunctions%2F-912451524)(p0: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [setVerticalOffsetEnabled](index.md#945440740%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [setVerticalOffsetEnabled](index.md#945440740%2FFunctions%2F-912451524)(p0: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |