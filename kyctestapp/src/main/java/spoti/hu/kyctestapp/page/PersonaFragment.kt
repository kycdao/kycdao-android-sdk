package spoti.hu.kyctestapp.page

import android.view.LayoutInflater
import android.view.ViewGroup
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentPersonaBinding

class PersonaFragment : BaseFragment<FragmentPersonaBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPersonaBinding {
        return FragmentPersonaBinding.inflate(inflater, container, false)
    }
}