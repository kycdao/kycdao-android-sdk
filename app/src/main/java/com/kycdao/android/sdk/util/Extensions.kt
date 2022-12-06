package com.kycdao.android.sdk.util

import com.kycdao.android.sdk.model.NativeCurrency
import org.web3j.abi.datatypes.generated.Uint32
import java.math.BigInteger
import kotlin.math.abs
import kotlin.math.log10
import kotlin.math.max

fun BigInteger.asHexString(): String {
	return "0x" + this.toString(16)
}
val UInt.yearsInSeconds
	get():UInt {
		return this * 365u * 24u * 60u * 60u
	}
val Int.seconds
	get():Long {
		return this * 1000L
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

fun BigInteger.decimalText(divisor: BigInteger, precision: Int = 3): String {
	val result = this.divideAndRemainder(divisor)
	val fullUnit = result[0]
	val remainder = result[1]

	val missingLeadingZeroCount = max(divisor.digitCount() - remainder.digitCount() - 1, 0)

	var remainderInTextForm = remainder.toString()
	if (remainderInTextForm.count() > precision) {
		remainderInTextForm = remainderInTextForm.dropLast(remainderInTextForm.count() - precision)
	}

	if (missingLeadingZeroCount > 0) {
		return "$fullUnit,${buildString { repeat(missingLeadingZeroCount) { append("0") } }}$remainderInTextForm"
	}
	return fullUnit.toString()
}

fun BigInteger.digitCount(): Int {
	return when (this) {
		BigInteger("0") -> 1
		else -> log10(abs(toDouble())).toInt() + 1
	}
}

fun String.web3jEncodedUint32(): Uint32 {
	return Uint32(BigInteger.valueOf(this.toLong()))
}

fun BigInteger.toText(currency: NativeCurrency) : String{
	return "${this.decimalText(currency.baseToNativeDivisor)} ${currency.symbol}"
}
