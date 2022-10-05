package com.kycdao.android.sdk.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val ARG_PARAM1 = "data"

abstract class BaseFragment<T> : Fragment() {

    private val gson : Gson by lazy { Gson() }

    protected fun setData(data: T): BaseFragment<T> {
        val arguments = arguments ?: Bundle()
        arguments.putString(ARG_PARAM1, gson.toJson(data))
        setArguments(arguments)
        return this
    }

    protected fun getData() : T {
        val data = arguments?.getString(ARG_PARAM1, "")
        return gson.fromJson(data, typeToken().type)
    }

    open fun typeToken(): TypeToken<T> = object : TypeToken<T>() {}

}