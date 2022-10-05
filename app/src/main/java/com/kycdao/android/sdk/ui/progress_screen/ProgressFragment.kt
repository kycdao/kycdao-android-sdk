package com.kycdao.android.sdk.ui.progress_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kycdao.android.sdk.ui.delegate.DiffListAdapter
import com.kycdao.android.sdk.ui.progress_screen.cell.cellStepItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kycdao.android.sdk.databinding.FragmentProgresWithButtonsBinding
import kycdao.android.sdk.databinding.FragmentProgressBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProgressFragment : Fragment() {
    private lateinit var binding: FragmentProgresWithButtonsBinding
    private lateinit var listAdapter: DiffListAdapter

    private val viewModel: ProgressViewModel by viewModel{
        parametersOf(this.activity)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProgresWithButtonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.connectWalletBtn.setOnClickListener{
            viewModel.connectToWallet()
        }
        binding.createSessionBtn.setOnClickListener{
            viewModel.createKycSession()
        }
        binding.login.setOnClickListener{
            viewModel.login()
        }
        binding.addPersonalInfoBtn.setOnClickListener {
            viewModel.savePersonalInfo()
        }
        binding.startPersona.setOnClickListener {
            viewModel.startPersona()
        }
        binding.selectNft.setOnClickListener {
            viewModel.selectNft()
        }
        binding.mintNFT.setOnClickListener {
            viewModel.mintNft()
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.myKycSession.collect{
                    if(it != null){
                        binding.nonceText.text = it.sessionData.nonce
                        binding.loginId.text = it.sessionData.user.id.toString()
                        binding.emailConfirmed.text = it.isEmailConfirmed().toString()
                    }
                }
            }
        }
       // setRecycleView()
    }

  //  private fun setRecycleView() {
  //      listAdapter =
  //          DiffListAdapter(cellStepItem())
  //      binding.stepList.apply {
  //          layoutManager = LinearLayoutManager(context)
  //          adapter = listAdapter
  //      }
//
  //      setListContent()
  //  }

  // private fun setListContent() {
  //     viewModel.listItems.observe(viewLifecycleOwner) {
  //         listAdapter.updateData(it)
  //         listAdapter.notifyDataSetChanged()
  //         if (it.isNotEmpty()) {
  //             binding.stepList.smoothScrollToPosition(it.size-1)
  //         }
  //     }
  // }
}