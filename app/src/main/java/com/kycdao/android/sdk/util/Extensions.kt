package com.kycdao.android.sdk.util

import java.math.BigInteger

fun BigInteger.asHexString() : String{
    return "0x"+this.toString(16)
}

val Int.seconds get():Long {
   return this *1000L
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
