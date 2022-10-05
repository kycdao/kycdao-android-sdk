package com.kycdao.android.sdk.ui.base

import com.google.gson.reflect.TypeToken

/**** Copy and change "Fragment" and "DataType" ****/
typealias Fragment = ExampleFragment    // fragment class name
typealias DataType = Boolean            // Example: simple data
//typealias DataType = Step             // Example: simple model
//typealias DataType = List<Step>       // Example: list
/******************************************/

class ExampleFragment : BaseFragment<DataType>() {

    /**** Copy this ****/
    companion object {
        @JvmStatic fun newInstance(data: DataType) = Fragment().setData(data)
    }
    override fun typeToken(): TypeToken<DataType> = object : TypeToken<DataType>() {}
    /*******************/


    /*
     * Data usage:
     * val data = getData()
     * Log.d("data", "$data")
     */
}

/*
 * Create fragment:
 * val exampleFragment = ExampleFragment.newInstance(true)
 */
