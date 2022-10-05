package com.kycdao.android.sdk.ui

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.google.android.material.appbar.AppBarLayout
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl.Companion.toHttpUrl
import java.math.BigInteger

fun ImageView.loadSvgWithCookie(url: String, cookieJar: CookieJar) {
    val request = ImageRequest.Builder(context)
        .data(url)
        .addHeader("Cookie", cookieHeader(cookieJar.loadForRequest(url.toHttpUrl())))
        .target(this)
        .build()

    ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()
        .enqueue(request)
}

private fun cookieHeader(cookies: List<Cookie>): String = buildString {
    cookies.forEachIndexed { index, cookie ->
        if (index > 0) append("; ")
        append(cookie.name).append('=').append(cookie.value)
    }
}

fun Fragment.setButtonEnabler(button: Button, livedata: LiveData<Boolean>) {
    livedata.distinctUntilChanged().observe(viewLifecycleOwner) { enabled ->
        button.isEnabled = enabled
        if (enabled) {
            button.animate().alpha(1f).apply {
                duration = 400
            }.start()
        } else {
            button.animate().alpha(0.4f).apply {
                duration = 400
            }.start()
        }
    }
}

fun Fragment.setHeaderShadow(shadow: View, appBarLayout: AppBarLayout) {
    val extended = MutableLiveData(true)

    extended.distinctUntilChanged().observe(viewLifecycleOwner) { isExtended ->
        if (!isExtended) {
            shadow.animate().alpha(0.3f).apply {
                duration = 300
            }.start()
        } else {
            shadow.animate().alpha(0f).apply {
                duration = 300
            }.start()
        }
    }

    appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
        if (verticalOffset == 0) {
            extended.postValue(true)
        } else if (verticalOffset != 0) {
            extended.postValue(false)
        }
    })
}

fun AppBarLayout.enableScrolling(enable: Boolean) {
    val behavior = (layoutParams as CoordinatorLayout.LayoutParams).behavior
    if (behavior is ToggleableAppBarLayoutBehavior) {
        behavior.enableScroll(enable)
    }
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

/**
 * - The [setup] function will only run, if the [condition] is true.
 * - If the [condition] is [false], the visibility will be [View.GONE],
 * otherwise the visibility will be [View.VISIBLE]
 **/
fun <T : View> T.setupAndShowOnCondition(condition: Boolean, setup: (view: T) -> Unit) {
    isVisible = condition
    if (condition) setup(this)
}

fun ImageView.setDrawable(resId: Int?) {
    if (resId == null) {
        setImageDrawable(null)
    } else {
        setImageResource(resId)
    }
}

fun String.convertBigInteger(): BigInteger {
    var radix = 10
    var index = 0
    var negative = false
    var result: BigInteger

    // Handle minus sign, if present
    if (startsWith("-")) {
        negative = true
        index++
    }

    // Handle radix specifier, if present
    if (startsWith("0x", index) || startsWith("0X", index)) {
        index += 2
        radix = 16
    } else if (startsWith("#", index)) {
        index++
        radix = 16
    } else if (startsWith("0", index) && length > 1 + index) {
        index++
        radix = 8
    }
    if (startsWith("-", index)) {
        throw NumberFormatException("Negative sign in wrong position")
    }
    try {
        result = BigInteger(substring(index), radix)
        result = if (negative) result.negate() else result
    } catch (e: NumberFormatException) {
        // If number is Long.MIN_VALUE, we'll end up here. The next line
        // handles this case, and causes any genuine format error to be
        // rethrown.
        val constant = if (negative) "-" + substring(index) else substring(index)
        result = BigInteger(constant, radix)
    }
    return result
}
