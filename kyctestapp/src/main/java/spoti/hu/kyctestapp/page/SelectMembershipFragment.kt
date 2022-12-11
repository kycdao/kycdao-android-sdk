package spoti.hu.kyctestapp.page

import android.view.LayoutInflater
import android.view.ViewGroup
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentSelectMembershipBinding

class SelectMembershipFragment : BaseFragment<FragmentSelectMembershipBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSelectMembershipBinding {
        return FragmentSelectMembershipBinding.inflate(inflater, container, false)
    }
}