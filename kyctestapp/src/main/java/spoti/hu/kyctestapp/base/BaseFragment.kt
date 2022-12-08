package spoti.hu.kyctestapp.base

import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import org.koin.android.ext.android.inject
import spoti.hu.kyctestapp.manager.SDKManager


abstract class BaseFragment<V : androidx.viewbinding.ViewBinding> : BaseBindingFragment<V>() {
    protected val sdk: SDKManager by inject()

    protected fun navigateWithAction(action: NavDirections) {
        try {
            findNavController().navigate(action)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}