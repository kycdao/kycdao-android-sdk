package com.kycdao.android.sdk.ui.progress_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kycdao.android.sdk.ui.delegate.DiffListAdapter
import com.kycdao.android.sdk.ui.progress_screen.cell.cellStepItem
import kycdao.android.sdk.databinding.FragmentProgressBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProgressFragment : Fragment() {
    private lateinit var binding: FragmentProgressBinding
    private lateinit var listAdapter: DiffListAdapter

    private val viewModel: ProgressViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycleView()
    }

    private fun setRecycleView() {
        listAdapter =
            DiffListAdapter(cellStepItem())
        binding.stepList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        setListContent()
    }

    private fun setListContent() {
        viewModel.listItems.observe(viewLifecycleOwner) {
            listAdapter.updateData(it)
            listAdapter.notifyDataSetChanged()
            if (it.isNotEmpty()) {
                binding.stepList.smoothScrollToPosition(it.size-1)
            }
        }
    }
}