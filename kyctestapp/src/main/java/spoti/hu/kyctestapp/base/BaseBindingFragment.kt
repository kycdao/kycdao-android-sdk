package spoti.hu.kyctestapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseBindingFragment<V : ViewBinding>(
    @LayoutRes contentLayoutId: Int = 0
) : Fragment(contentLayoutId) {
    private var _binding: V? = null

    // This property is only valid between onCreateView and onDestroyView.
    protected val binding get() = _binding!!
    protected val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = createBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): V
}

