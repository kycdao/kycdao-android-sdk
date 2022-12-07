package spoti.hu.kyctestapp.base

import org.koin.android.ext.android.inject
import spoti.hu.kyctestapp.manager.SDKManager


abstract class BaseFragment<V : androidx.viewbinding.ViewBinding> : BaseBindingFragment<V>() {
    protected val sdk: SDKManager by inject()
}