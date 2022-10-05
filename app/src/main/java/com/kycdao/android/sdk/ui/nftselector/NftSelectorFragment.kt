package com.kycdao.android.sdk.ui.nftselector

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.reflect.TypeToken
import com.kycdao.android.sdk.model.AvailableImage
import com.kycdao.android.sdk.ui.KycActivityResultContract
import com.kycdao.android.sdk.ui.base.BaseFragment
import com.kycdao.android.sdk.ui.delegate.DiffListAdapter
import com.kycdao.android.sdk.ui.nftselector.cell.cellRadioGroup
import com.kycdao.android.sdk.ui.setButtonEnabler
import kycdao.android.sdk.databinding.FragmentNftSelectorBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

typealias DataType = List<AvailableImage>

class NftSelectorFragment : BaseFragment<DataType>() {

    companion object {
        @JvmStatic fun newInstance(data: DataType) = NftSelectorFragment().setData(data)
    }
    override fun typeToken(): TypeToken<DataType> = object : TypeToken<DataType>() {}

    private lateinit var binding: FragmentNftSelectorBinding
    private lateinit var listAdapter: DiffListAdapter

    private val viewModel by viewModel<NftSelectorViewModel> { parametersOf(getData()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNftSelectorBinding.inflate(inflater, container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonEnabler(binding.acceptButton, viewModel.isButtonEnabled)
        setRecycleView()
        setButtonAction()
        viewModel.outEvents.observe(viewLifecycleOwner) {
            if (it is ContinueEvent) {
                requireActivity().setResult(Activity.RESULT_OK, Intent().apply {
                    putExtra(KycActivityResultContract.RESULT_KEY, it.selectedId)
                })
                requireActivity().finish()
            }
        }
    }

    private fun setRecycleView() {
        listAdapter =
            DiffListAdapter(cellRadioGroup(viewModel.events))
        binding.radioGroup.apply {

            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        setListContent()
    }

    private fun setButtonAction() {
        binding.acceptButton.setOnClickListener {
            viewModel.onContinue()
        }
    }

    private fun setListContent() {
        viewModel.currentItems.observe(viewLifecycleOwner) { list ->
            listAdapter.updateData(list)
        }
    }
}