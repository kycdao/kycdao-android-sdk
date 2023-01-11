package spoti.hu.kyctestapp.base

import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import org.koin.android.ext.android.inject
import spoti.hu.kyctestapp.manager.SDKManager
import spoti.hu.kyctestapp.navigation.NavigationManager


abstract class BaseFragment<V : androidx.viewbinding.ViewBinding> : BaseBindingFragment<V>() {
    protected val sdk: SDKManager by inject()
    protected val navigation: NavigationManager by inject()

    protected fun navigateWithAction(action: NavDirections) {
        try {
            findNavController().navigate(action)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    protected fun navigateNext(){
        try {
            findNavController().navigate(navigation.getNextDestination())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}