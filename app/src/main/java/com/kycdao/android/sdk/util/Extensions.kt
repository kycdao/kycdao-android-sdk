package com.kycdao.android.sdk.util

import java.math.BigInteger

fun BigInteger.asHexString() : String{
    return "0x"+this.toString(16)
}

val Int.seconds get():Long {
   return this *1000L
}